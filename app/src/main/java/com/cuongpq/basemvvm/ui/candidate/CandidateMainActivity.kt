package com.cuongpq.basemvvm.ui.candidate

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cuongpq.basemvvm.R
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.databinding.ActivityCandidateBinding
import com.cuongpq.basemvvm.ui.base.activity.BaseMVVMActivity
import com.cuongpq.basemvvm.ui.employer.myjob.MyJobFragment
import com.cuongpq.basemvvm.ui.noticification.NoticificationFragment
import com.cuongpq.basemvvm.ui.profile.ProfileFragment
import com.cuongpq.basemvvm.ui.utils.OpenFragmentUtils

class CandidateMainActivity : BaseMVVMActivity<CandidateMainCallBack,CandidateMainModelView>(),CandidateMainCallBack {

    private var backPressTime: Long = 0
    private var user : User? = null

    override fun getLayoutMain(): Int {
        return R.layout.activity_candidate
    }

    override fun setEvents() {

    }

    override fun initComponents() {
        user = intent.getSerializableExtra("user") as User?
        getBindingData().candidateMainViewModel = mModel
        getFragmet(MyJobFragment())
        onClickMenu()
    }

    override fun getBindingData() = mBinding as ActivityCandidateBinding

    override fun getViewModel(): Class<CandidateMainModelView> {
        return CandidateMainModelView::class.java
    }
    override fun error(id: String, error: Throwable) {
        showMessage(error.message!!)
    }
    override fun onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()) {
            finishAffinity()
            System.exit(0)
            return
        } else {
            Toast.makeText(this, "Nhấn 2 lần liên tiếp để thoát app", Toast.LENGTH_SHORT).show()
        }
        backPressTime = System.currentTimeMillis()
    }
    private fun onClickMenu(){
        getBindingData().bottomNav.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.myJobFragment2 -> {
                    getFragmet(MyJobFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profileFragment2 -> {
                    getFragmet(ProfileFragment(user))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.notificationFragment2 -> {
                    getFragmet(NoticificationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
    private fun getFragmet(fragment: Fragment){
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentMain2,fragment)
        fragmentTransaction.commit()
    }
}