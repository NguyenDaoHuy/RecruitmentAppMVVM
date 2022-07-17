package com.cuongpq.basemvvm.ui.employer.create_job.create_status

import android.content.Context
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class CreateStatusViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<CreateStatusCallBack>(appDatabase,interactCommon,scheduler)  {

    lateinit var idJob : String

    companion object{
        const val CLICK_DONE = 1
        const val SET_STATUS_SUCCESS =2
    }

    fun onClickDone(){
        uiEventLiveData.value = CLICK_DONE
    }

    fun setStatus(context: Context, status : Int){
        val sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        sqLiteHelper.QueryData("UPDATE Job4 SET status = '$status' WHERE JobCode ='$idJob'")
        uiEventLiveData.value = SET_STATUS_SUCCESS
    }

}