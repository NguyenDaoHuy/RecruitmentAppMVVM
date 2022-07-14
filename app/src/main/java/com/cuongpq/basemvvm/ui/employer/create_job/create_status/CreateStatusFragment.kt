package com.cuongpq.basemvvm.ui.employer.create_job.create_status

import android.widget.Toast
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentSetStatusJobBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel

class CreateStatusFragment(var idJob : String?) : BaseMvvmFragment<CreateStatusCallBack,CreateStatusViewModel>(),CreateStatusCallBack{

    private var status : Int = 0

    override fun initComponents() {
        getBindingData().createStatusViewModel = mModel
        mModel.idJob = idJob!!
        mModel.uiEventLiveData.observe(this){
            when(it){
                BaseViewModel.FINISH_ACTIVITY -> finishActivity()
                CreateStatusViewModel.CLICK_DONE -> onClickDone()
                CreateStatusViewModel.SET_STATUS_SUCCESS -> onSetStatusSuccess()
            }
        }
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_set_status_job
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentSetStatusJobBinding

    override fun getViewModel(): Class<CreateStatusViewModel> {
        return CreateStatusViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    fun onClickDone(){
        if(getBindingData().rdPublic.isChecked){
            status = 1
        }else if(getBindingData().rdPrivate.isChecked){
            status = 2
        }
        mModel.setStatus(requireContext(),status)
    }
    fun onSetStatusSuccess(){
        Toast.makeText(context,"Tạo thành công",Toast.LENGTH_SHORT).show()
    }
    companion object{
        val TAG = CreateStatusFragment::class.java.name
    }
}