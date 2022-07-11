package com.cuongpq.basemvvm.ui.profile.update_skill

import com.cuongpq.basemvvm.data.local.AppDatabase
import com.cuongpq.basemvvm.data.remote.InteractCommon
import com.cuongpq.basemvvm.ui.base.viewmodel.BaseViewModel
import java.util.concurrent.Executor
import javax.inject.Inject

class UpdateSkillViewModel @Inject constructor(
    appDatabase: AppDatabase,
    interactCommon: InteractCommon,
    scheduler: Executor
) : BaseViewModel<UpdateSkillCallBack>(appDatabase, interactCommon, scheduler){
    init {

    }
}