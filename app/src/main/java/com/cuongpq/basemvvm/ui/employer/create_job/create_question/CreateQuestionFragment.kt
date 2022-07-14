package com.cuongpq.basemvvm.ui.employer.create_job.create_question

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.job.question.Question
import com.cuongpq.basemvvm.databinding.FragmentCreateQuestionBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.CreateRequestCallBack
import com.cuongpq.basemvvm.ui.employer.create_job.create_right.CreateRightFragment

class CreateQuestionFragment(var idJob : String?) :
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
           }
       }
        mModel.getQuestion(context)
        initRecyclerViewQuestion()
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

    fun onClickAddQuestion(){
        edQuestion = getBindingData().edQuestion.text.toString().trim()
        if(edQuestion!!.isEmpty()){
            Toast.makeText(context,"Nhập dữ liệu", Toast.LENGTH_SHORT).show()
        }else{
            mModel.addQuestion(edQuestion,context)
        }
    }
    fun onAddQuestionSuccess(){
        Toast.makeText(context,"Thêm câu hỏi thành công",Toast.LENGTH_SHORT).show()
        getBindingData().edQuestion.setText("")
        mModel.getQuestion(context)
        Log.e(mModel.getListQuestion().size.toString(),"Test")
        getBindingData().rcvQuestion.adapter!!.notifyDataSetChanged()
    }
    fun initRecyclerViewQuestion(){
        val questionAdapter = QuestionAdapter(this)
        getBindingData().rcvQuestion.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvQuestion.setAdapter(questionAdapter)
    }

    fun onDeleteQuestionSuccess(){
        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
        mModel.getQuestion(context)
        getBindingData().rcvQuestion.adapter!!.notifyDataSetChanged()
    }

    override fun count(): Int {
        if (mModel.getListQuestion() == null){
            return 0
        }
        return mModel.getListQuestion().size
    }

    override fun getQuestion(position: Int): Question {
        return mModel.getListQuestion().get(position)
    }

    override fun onClickDeleteQuestion(position: Int) {
        var question = mModel.getListQuestion().get(position)
        mModel.deleteQuestion(question,context)
    }
    fun onClickNext(){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain, CreateRightFragment(idJob))
        fragmentTransaction.addToBackStack(CreateRightFragment.TAG)
        fragmentTransaction.commit()
    }
    companion object{
        val TAG = CreateQuestionFragment::class.java.name
    }

}