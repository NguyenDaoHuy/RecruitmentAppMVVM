package com.cuongpq.basemvvm.ui.employer.create_job.create_description

import android.app.Activity
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class AddJobViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<CreateJobCallBack>(appDatabase, interactCommon, scheduler) {

    lateinit var jobCode : String
    lateinit var jobName : String
    lateinit var description :String
    lateinit var idAccount : String
    var idCompany : Int = 0

    companion object{
        val ADD_JOB_SUCCESS = 2
        val ADD_JOB_ERROR = 3
        val NEXT_TO_REQUEST = 4
        val ADD_JOB_EXISTS = 5
    }

    init {

    }

    fun onCLickNext(){
        uiEventLiveData.value = NEXT_TO_REQUEST
    }

    fun addJobToDatabase(activity : Activity, user: User){
        val sqLiteHelper = SQLiteHelper(activity, "Data.sqlite", null, 5)
        idAccount = user.idAccount
        val cursor = sqLiteHelper.GetData("SELECT * FROM Job4 WHERE JobCode = '$jobCode'")
        if (cursor.count <= 0) {
            val data = sqLiteHelper!!.GetData("SELECT * FROM User WHERE IdAccount = '$idAccount'")
            while (data.moveToNext()) {
                idCompany = data.getInt(8)
            }
            if(idCompany == 0){
                uiEventLiveData.value = ADD_JOB_ERROR
            }else{
                sqLiteHelper!!.QueryData("INSERT INTO Job4 VALUES(null,'$jobCode','$jobName','$description','$idAccount','$idCompany','','','')")
                uiEventLiveData.value = ADD_JOB_SUCCESS
            }
        } else {
                uiEventLiveData.value = ADD_JOB_EXISTS
        }


    }
}