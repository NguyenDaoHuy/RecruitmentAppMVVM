package com.cuongpq.basemvvm.di.builder

import com.cuongpq.basemvvm.ui.candidate.CandidateMainActivity
import com.cuongpq.basemvvm.ui.employer.EmployerMainActivity
import com.cuongpq.basemvvm.ui.login.FirstActivity
import com.cuongpq.basemvvm.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeFirstActivity(): FirstActivity

    @ContributesAndroidInjector
    abstract fun contributeEmployerMaintActivity(): EmployerMainActivity

    @ContributesAndroidInjector
    abstract fun contributeCandidateMainActivity(): CandidateMainActivity
}