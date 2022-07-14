package com.cuongpq.basemvvm.ui.profile

import android.content.ContentValues
import android.util.Log
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.login.signin.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.concurrent.Executor
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<ProfileCallBack>(appDatabase, interactCommon, scheduler) {

    private var auth : FirebaseAuth? = null

    companion object{
        val LOG_OUT = 1000
        val GO_UPDATE_SKILL = 1001
        val SHOW_DIALOG = 1002
        val ON_CLICK_AVT = 1003
        val ON_CLICK_SET_AVT = 1004
        val ON_CLICK_COMPANY = 1005
    }
    init {
        auth = FirebaseAuth.getInstance()
    }

    fun onClickUpdateSkill(){
        uiEventLiveData.value = GO_UPDATE_SKILL
    }

    fun onCLickLogOut(){
        uiEventLiveData.value = LOG_OUT
    }

    fun onCLickShowDialog(){
        uiEventLiveData.value = SHOW_DIALOG
    }

    fun onLogOut(){
        FirebaseAuth.getInstance().signOut()
    }
    fun onClickAvatar(){
        uiEventLiveData.value = ON_CLICK_AVT
    }
    fun onClickSetAvatar(){
        uiEventLiveData.value = ON_CLICK_SET_AVT
    }
    fun onClickCompany(){
        uiEventLiveData.value = ON_CLICK_COMPANY
    }
}