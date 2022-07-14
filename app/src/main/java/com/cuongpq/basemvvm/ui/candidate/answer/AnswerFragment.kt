package com.cuongpq.basemvvm.ui.candidate.answer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.data.model.job.question.Question
import com.cuongpq.basemvvm.databinding.FragmentAnswerBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.login.FirstActivity

class AnswerFragment(private val job: Job) : BaseMvvmFragment<AnswerCallBack,AnswerViewModel>(),AnswerCallBack,AnswerTheQuestionAdapter.IAnswerTheQuestion{

    override fun initComponents() {
        getBindingData().answerViewModel = mModel
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                AnswerViewModel.CLICK_COMFIRM -> onClickComfirm()
            }
        }
        initRecyclerView()
    }

    private fun onClickComfirm() {

    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_answer
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentAnswerBinding

    override fun getViewModel(): Class<AnswerViewModel> {
        return AnswerViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    companion object{
        val TAG = AnswerFragment::class.java.name
    }

    override fun count(): Int {
        return job.listQuestion!!.size
    }

    override fun getQuestion(position: Int): Question {
        return job.listQuestion!!.get(position)
    }

    fun initRecyclerView(){
        val answerTheQuestionAdapter = AnswerTheQuestionAdapter(this)
        getBindingData().rcvAnswerToQuestion.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvAnswerToQuestion.setAdapter(answerTheQuestionAdapter)
    }
}