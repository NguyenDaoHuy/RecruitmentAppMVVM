package com.cuongpq.basemvvm.ui.employer.create_job.create_right

import android.content.Context
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class CreateRightViewModel  @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<CreateRightCallBack>(appDatabase, interactCommon, scheduler) {

    lateinit var idJob : String
    companion object{
        val ON_CLICK_NEXT = 1
        val ADD_RIGHT_SUCCESS = 2
    }
    init {

    }
    fun onClickNext(){
        uiEventLiveData.value = ON_CLICK_NEXT
    }
    fun setRightAndAmount(right : String,amount : Int,context : Context){
        val  sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("UPDATE Job4 SET right = '$right' AND amount = '$amount' WHERE JobCode ='$idJob'")
        uiEventLiveData.value = ADD_RIGHT_SUCCESS
    }
}