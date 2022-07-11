 package com.cuongpq.basemvvm.ui.noticification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.FragmentNoticificationBinding
import com.cuongpq.basemvvm.ui.base.fragment.BaseMvvmFragment

 class NoticificationFragment : BaseMvvmFragment<NotificationCallBack,NotificationViewModel>(),NotificationCallBack {

     override fun setEvents() {

     }

     override fun initComponents() {
        getBindingData().noticifiViewModel = mModel
     }

     override fun getBindingData() = mBinding as FragmentNoticificationBinding

     override fun getViewModel(): Class<NotificationViewModel> {
         return NotificationViewModel::class.java
     }
     override fun error(id: String, error: Throwable) {
         showMessage(error.message!!)
     }

     override fun getLayoutMain(): Int {
         return R.layout.fragment_noticification
     }
 }