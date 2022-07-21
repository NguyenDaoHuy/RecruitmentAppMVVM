package com.cuongpq.basemvvm.ui.candidate.answer

import android.content.Context
import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.model.User
import com.cuongpq.basemvvm.data.model.job.Job
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.data.sqlite.SQLiteHelper
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class AnswerViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<AnswerCallBack>(appDatabase, interactCommon, scheduler) {
    private var active : Int? = null
    companion object{
        const val CLICK_COMFIRM = 1
        const val APPLY_ERROR = 2
        const val APPLY_SUCCESS = 3
        const val GO_TO_PAYER = 4
    }

    fun onClickComfirm(){
        uiEventLiveData.value = CLICK_COMFIRM
    }
    fun setApply(candidate : User,job: Job,context: Context){
        val sqLiteHelper = SQLiteHelper(context, "Data.sqlite", null, 5)
        val dataActive = sqLiteHelper.GetData("SELECT * FROM UserActive WHERE IdUser = '${candidate.idAccount}'")
        while (dataActive.moveToNext()){
            active = dataActive.getInt(2)
        }
        if(active == 1){
            val cursor = sqLiteHelper.GetData("SELECT * FROM Apply WHERE IdCandidate = '${candidate.idAccount}' AND IdJob ='${job.idJob}'")
            if (cursor.count <= 0) {
                sqLiteHelper.QueryData("INSERT INTO Apply VALUES(null,'${candidate.idAccount}','${job.idJob}','${job.employer!!.idAccount}')")
                uiEventLiveData.value = APPLY_SUCCESS
            }else{
                uiEventLiveData.value = APPLY_ERROR
            }
        }else if(active == 0){
             uiEventLiveData.value = GO_TO_PAYER
        }
    }
}