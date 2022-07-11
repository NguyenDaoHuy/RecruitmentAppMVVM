package com.cuongpq.basemvvm.ui.candidate.job_information


import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentJobInformationBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment

class JobInformationFragment : BaseMvvmFragment<JobInformationCallBack,JobInformationViewModel>(),JobInformationCallBack {

    override fun initComponents() {
        getBindingData().jobInforViewModel = mModel
    }


    override fun getLayoutMain(): Int {
        return R.layout.fragment_job_information
    }

    override fun setEvents() {

    }

    override fun getBindingData() = mBinding as FragmentJobInformationBinding

    override fun getViewModel(): Class<JobInformationViewModel> {
        return JobInformationViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

}