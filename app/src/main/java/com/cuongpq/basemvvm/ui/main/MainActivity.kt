package com.cuongpq.basemvvm.ui.main

import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.ActivityMainBinding
import com.cuongpq.basemvvm.ui.base.activity.BaseMVVMActivity
import com.cuongpq.basemvvm.ui.utils.OpenFragmentUtils

class MainActivity : BaseMVVMActivity<MainCallBack, MainViewModel>(), MainCallBack {

    override fun getLayoutMain() = R.layout.activity_main

    override fun setEvents() {
    }

    override fun initComponents() {
        getBindingData().viewModel = mModel
        OpenFragmentUtils.openUserFragment(supportFragmentManager)
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }

    override fun getBindingData() = mBinding as ActivityMainBinding

}