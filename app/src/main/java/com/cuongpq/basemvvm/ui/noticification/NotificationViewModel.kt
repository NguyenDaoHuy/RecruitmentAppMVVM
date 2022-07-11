package com.cuongpq.basemvvm.ui.noticification

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import com.cuongpq.basemvvm.ui.profile.ProfileCallBack
import java.util.concurrent.Executor
import javax.inject.Inject

class NotificationViewModel  @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<NotificationCallBack>(appDatabase, interactCommon, scheduler) {
    init {

    }
}