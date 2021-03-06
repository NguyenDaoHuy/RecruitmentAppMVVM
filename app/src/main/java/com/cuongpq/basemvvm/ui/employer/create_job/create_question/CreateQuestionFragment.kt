package com.cuongpq.basemvvm.ui.employer.create_job.create_question

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.question.Question
import com.cuongpq.basemvvm.databinding.FragmentCreateQuestionBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.CreateRequestCallBack
import com.cuongpq.basemvvm.ui.employer.create_job.create_right.CreateRightFragment
import com.cuongpq.basemvvm.ui.employer.job.my_job.MyJobFragment

class CreateQuestionFragment(var idJob : String?,private var user: User?,private var type : Int) :
    BaseMvvmFragment<CreateQuestionCallBack,CreateQuestionViewModel>(),CreateRequestCallBack,QuestionAdapter.IQuestionAdapter{

    private var edQuestion : String? = null

    override fun initComponents() {
       getBindingData().createQuestionViewModel = mModel
       mModel.idJob = idJob!!
       mModel.uiEventLiveData.observe(this){
           when(it){
               BaseViewModel.FINISH_ACTIVITY -> finishActivity()
               CreateQuestionViewModel.ADD_QUESTION -> onClickAddQuestion()
               CreateQuestionViewModel.ADD_QUESTION_SUCCESS -> onAddQuestionSuccess()
               CreateQuestionViewModel.DELETE_QUESTION_SUCCESS -> onDeleteQuestionSuccess()
               CreateQuestionViewModel.ON_CLICK_NEXT_TO_RIGHT -> onClickNext()
               CreateQuestionViewModel.ON_CLICK_UPDATE_QUESTION -> onClickUpdate()
           }
       }
        mModel.getQuestion(context)
        initRecyclerViewQuestion()
        checkTypeOfView()
    }

    private fun checkTypeOfView(){
        if(type == 1){
            getBindingData().btnNext.visibility = View.VISIBLE
            getBindingData().btnUpdateInQuestion.visibility = View.GONE
        }else if(type == 2){
            getBindingData().btnNext.visibility = View.GONE
            getBindingData().btnUpdateInQuestion.visibility = View.VISIBLE
        }
    }
    override fun getLayoutMain(): Int {
        return R.layout.fragment_create_question
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentCreateQuestionBinding


    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    override fun getViewModel(): Class<CreateQuestionViewModel> {
        return CreateQuestionViewModel::class.java
    }

    private fun onClickAddQuestion(){
        edQuestion = getBindingData().edQuestion.text.toString().trim()
        if(edQuestion!!.isEmpty()){
            Toast.makeText(context,"Nh???p d??? li???u", Toast.LENGTH_SHORT).show()
        }else{
            mModel.addQuestion(edQuestion,context)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun onAddQuestionSuccess(){
        getBindingData().edQuestion.setText("")
        mModel.getQuestion(context)
        Log.e(mModel.getListQuestion().size.toString(),"Test")
        getBindingData().rcvQuestion.adapter!!.notifyDataSetChanged()
    }
    private fun initRecyclerViewQuestion(){
        val questionAdapter = QuestionAdapter(this)
        getBindingData().rcvQuestion.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvQuestion.adapter = questionAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onDeleteQuestionSuccess(){
        mModel.getQuestion(context)
        getBindingData().rcvQuestion.adapter!!.notifyDataSetChanged()
    }

    override fun count(): Int {
        return mModel.getListQuestion().size
    }

    override fun getQuestion(position: Int): Question {
        return mModel.getListQuestion()[position]
    }

    override fun onClickDeleteQuestion(position: Int) {
        val question = mModel.getListQuestion()[position]
        mModel.deleteQuestion(question,context)
    }
    fun onClickNext(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, CreateRightFragment(idJob,user))
        fragmentTransaction.addToBackStack(CreateRightFragment.TAG)
        fragmentTransaction.commit()
    }
    private fun onClickUpdate(){
        Toast.makeText(context,"C???p nh???p th??nh c??ng",Toast.LENGTH_SHORT).show()
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, MyJobFragment(user))
        fragmentTransaction.addToBackStack(MyJobFragment.TAG)
        fragmentTransaction.commit()
    }
    companion object{
        val TAG = CreateQuestionFragment::class.java.name
    }

}