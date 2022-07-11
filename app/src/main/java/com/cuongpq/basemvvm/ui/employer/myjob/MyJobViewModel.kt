package com.cuongpq.basemvvm.ui.employer.myjob

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.main.user.UserCallBack
import java.util.concurrent.Executor
import javax.inject.Inject

class MyJobViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<MyJobCallBack>(appDatabase, interactCommon, scheduler) {

    init {

    }
    fun onClickAddJob(){
        uiEventLiveData.value = GO_ADD_JOB
    }
    companion object{
        val GO_ADD_JOB = 1000

    }
}