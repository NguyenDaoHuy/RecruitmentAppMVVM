package com.cuongpq.basemvvm.ui.login

import android.os.Handler
import android.view.View
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.databinding.ActivityFirstBinding
import com.cuongpq.basemvvm.ui.base.activity.BaseMVVMActivity
import com.cuongpq.basemvvm.ui.login.signin.LoginFragment
import com.cuongpq.basemvvm.ui.utils.OpenFragmentUtils

class FirstActivity : BaseMVVMActivity<FirstCallBack,FirstViewModel>(),FirstCallBack{

    override fun getLayoutMain() = R.layout.activity_first

    override fun setEvents() {

    }

    override fun initComponents() {
        getBindingData().firstViewModel = mModel

        Handler().postDelayed({
            getFragment(LoginFragment())
            getBindingData().imgSplash.visibility = View.GONE
            getBindingData().txtSplash.visibility = View.GONE
        },1500)
    }

    override fun getBindingData() = mBinding as ActivityFirstBinding

    override fun getViewModel(): Class<FirstViewModel> {
        return FirstViewModel::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    private fun getFragment(fragment : Fragment?){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentLogin1,fragment!!)
        fragmentTransaction.commit()
    }
}