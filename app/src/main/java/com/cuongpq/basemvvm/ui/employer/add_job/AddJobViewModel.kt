package com.cuongpq.basemvvm.ui.employer.add_job

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class AddJobViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<AddJobCallBack>(appDatabase, interactCommon, scheduler) {

    init {

    }
}