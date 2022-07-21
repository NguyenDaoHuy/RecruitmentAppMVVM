package com.cuongpq.basemvvm.ui.candidate.answer

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.data.model.job.question.Question
import com.cuongpq.basemvvm.databinding.FragmentAnswerBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.candidate.list_job.ListJobSearchFragment
import com.cuongpq.basemvvm.ui.payer.PayerActivity

class AnswerFragment(private val job: Job,private val user: User) : BaseMvvmFragment<AnswerCallBack,AnswerViewModel>(),AnswerCallBack,AnswerTheQuestionAdapter.IAnswerTheQuestion{

    override fun initComponents() {
        getBindingData().answerViewModel = mModel
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                AnswerViewModel.CLICK_COMFIRM -> onClickComfirm()
                AnswerViewModel.APPLY_SUCCESS -> onApplySuccess()
                AnswerViewModel.APPLY_ERROR -> onApplyError()
                AnswerViewModel.GO_TO_PAYER -> goToPayerActivity()
            }
        }
        initRecyclerView()
    }

    private fun onApplyError() {
        Toast.makeText(context,"Bạn đã ứng tuyển công việc này",Toast.LENGTH_SHORT).show()
    }

    private fun onApplySuccess() {
        val view = View.inflate(context, R.layout.dialog_view_apply_success, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        dialog.setCancelable(false)
        val btnComfirm  = view.findViewById<Button>(R.id.btnComfirm)
        btnComfirm.setOnClickListener {
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentMain2,ListJobSearchFragment(user))
            fragmentTransaction.commit()
            dialog.dismiss()
        }
    }

    private fun goToPayerActivity(){
        val intent = Intent(context, PayerActivity::class.java)
        intent.putExtra("userId",user!!.idAccount)
        startActivity(intent)
    //    Toast.makeText(context,"Bạn phải thanh toán để sử dụng app!", Toast.LENGTH_SHORT).show()
    }

//    private fun onClickComfirm() {
//          val view = View.inflate(context,R.layout.dialog_view,null)
//          val builder = AlertDialog.Builder(context)
//          builder.setView(view)
//          val dialog = builder.create()
//          dialog.show()
//          dialog.window?.setBackgroundDrawableResource(R.color.transparent)
//          dialog.setCancelable(false)
//          var btnBuy = view.findViewById<Button>(R.id.btnBuy)
//          var btnTrial30Days = view.findViewById<Button>(R.id.btnTrial30Days)
//          btnBuy.setOnClickListener {
//              Toast.makeText(context,"Chưa hoàn thiện",Toast.LENGTH_SHORT).show()
//          }
//          btnTrial30Days.setOnClickListener {
//              Toast.makeText(context,"Thành công",Toast.LENGTH_SHORT).show()
//              dialog.dismiss()
//          }
//    }

    private fun onClickComfirm() {
       mModel.setApply(user,job, requireContext())
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
        return job.listQuestion!![position]
    }

    private fun initRecyclerView(){
        val answerTheQuestionAdapter = AnswerTheQuestionAdapter(this)
        getBindingData().rcvAnswerToQuestion.layoutManager = LinearLayoutManager(context)
        getBindingData().rcvAnswerToQuestion.adapter = answerTheQuestionAdapter
    }
}