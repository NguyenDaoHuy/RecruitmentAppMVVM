package com.cuongpq.basemvvm.ui.payer

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class PayerActivityViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<PayerActivityCallBack>(appDatabase, interactCommon, scheduler) {
    companion object{
        const val ON_CLICK_PAY = 1
    }
    fun onClickPay(){
        uiEventLiveData.value = ON_CLICK_PAY
    }
}