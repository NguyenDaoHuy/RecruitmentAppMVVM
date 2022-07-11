package com.cuongpq.basemvvm.ui.employer.add_job

import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentAddJobBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment

class AddJobFragment : BaseMvvmFragment<AddJobCallBack,AddJobViewModel>(),AddJobCallBack {

    override fun setEvents() {

    }

    override fun initComponents() {
        getBindingData().addJobViewModel = mModel
    }

    override fun getBindingData() = mBinding as FragmentAddJobBinding

    override fun getViewModel(): Class<AddJobViewModel> {
        return AddJobViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    override fun getLayoutMain(): Int {
        return R.layout.fragment_add_job
    }
    companion object {
        val TAG = AddJobFragment::class.java.name
    }

}