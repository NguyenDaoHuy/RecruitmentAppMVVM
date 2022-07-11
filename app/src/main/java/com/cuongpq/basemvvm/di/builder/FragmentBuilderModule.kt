package com.cuongpq.basemvvm.di.builder

import com.cuongpq.basemvvm.ui.candidate.job_information.JobInformationFragment
import com.cuongpq.basemvvm.ui.employer.add_job.AddJobFragment
import com.cuongpq.basemvvm.ui.employer.myjob.MyJobFragment
import com.cuongpq.basemvvm.ui.login.signin.LoginFragment
import com.cuongpq.basemvvm.ui.login.signup.RegisterFragment
import com.cuongpq.basemvvm.ui.main.user.UserFragment
import com.cuongpq.basemvvm.ui.noticification.NoticificationFragment
import com.cuongpq.basemvvm.ui.profile.ProfileFragment
import com.cuongpq.basemvvm.ui.profile.update_skill.UpdateSkillFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeMyJobFragment() : MyJobFragment

    @ContributesAndroidInjector
    abstract fun contributeNoticificationFragment() : NoticificationFragment

    @ContributesAndroidInjector
    abstract fun contributeAddJobFragment() : AddJobFragment

    @ContributesAndroidInjector
    abstract fun contributeUpdateSkillFragment() : UpdateSkillFragment

    @ContributesAndroidInjector
    abstract fun contributeJobInformationFragment() : JobInformationFragment
}