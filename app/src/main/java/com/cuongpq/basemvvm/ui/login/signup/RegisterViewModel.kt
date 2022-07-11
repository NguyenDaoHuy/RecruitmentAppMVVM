package com.cuongpq.basemvvm.ui.login.signup

import androidx.lifecycle.MutableLiveData
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.Executor
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<RegisterCallBack>(appDatabase, interactCommon, scheduler) {

    private var firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>? = null
    private var auth : FirebaseAuth? = null
    lateinit var email: String
    lateinit var password: String
    lateinit var userName: String
    var age: Int = 0
    lateinit var phone: String
    var permission : Int = 0
    private var mAuth: FirebaseAuth? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null

    init {
        auth = FirebaseAuth.getInstance()
        firebaseUserMutableLiveData = MutableLiveData()
        if (auth!!.currentUser != null) {
            firebaseUserMutableLiveData!!.postValue(auth!!.currentUser)
        }
    }

    companion object {
        val REGISTER_SUCCESS = 1000
        val BACK_LOGIN = 1002
        val REGISTER_ERROR = 1003
        val GET_DATA_FROM_UI_AND_REGISTER = 1005
    }

    fun onClickBackLogInFragment() {
        uiEventLiveData.value = BACK_LOGIN
    }

    fun onRegister() {
        auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseDatabase = FirebaseDatabase.getInstance()
                databaseReference = firebaseDatabase!!.getReference()
                val IdAcount: String = task.getResult().getUser()!!.getUid()
                val user = User(IdAcount,email,password,userName,age,phone,permission)
                databaseReference!!.child(IdAcount).setValue(user)
                uiEventLiveData.value = REGISTER_SUCCESS
            } else {
                uiEventLiveData.value = REGISTER_ERROR
            }
        }
    }
    fun onClickSignUp(){
        uiEventLiveData.value = GET_DATA_FROM_UI_AND_REGISTER
    }
}