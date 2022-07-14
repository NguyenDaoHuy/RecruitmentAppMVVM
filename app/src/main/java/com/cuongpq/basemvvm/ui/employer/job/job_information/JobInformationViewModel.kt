package com.cuongpq.basemvvm.ui.employer.job.job_information

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.employer.job.job_information.JobInformationCallBack
import java.util.concurrent.Executor
import javax.inject.Inject

class JobInformationViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<JobInformationCallBack>(appDatabase, interactCommon, scheduler) {

    companion object{
        val ON_CLICK_APPLY = 1
    }
    init {

    }
    fun onClickApply(){
        uiEventLiveData.value = ON_CLICK_APPLY
    }
}