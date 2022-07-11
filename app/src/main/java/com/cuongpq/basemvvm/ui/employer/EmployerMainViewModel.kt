package com.cuongpq.basemvvm.ui.employer

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.main.MainCallBack
import java.util.concurrent.Executor
import javax.inject.Inject

class EmployerMainViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<EmployerMainCallBack>(appDatabase, interactCommon, scheduler) {

    init {

    }
}