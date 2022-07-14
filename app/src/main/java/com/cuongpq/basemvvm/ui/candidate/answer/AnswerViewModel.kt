package com.cuongpq.basemvvm.ui.candidate.answer

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class AnswerViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<AnswerCallBack>(appDatabase, interactCommon, scheduler) {
    companion object{
        val CLICK_COMFIRM = 1
    }
    init {

    }
    fun onClickComfirm(){
        uiEventLiveData.value = CLICK_COMFIRM
    }
}