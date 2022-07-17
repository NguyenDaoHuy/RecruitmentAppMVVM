package com.cuongpq.basemvvm.di.builder

import com.cuongpq.basemvvm.ui.candidate.answer.AnswerFragment
import com.cuongpq.basemvvm.ui.candidate.list_job.ListJobSearchFragment
import com.cuongpq.basemvvm.ui.employer.company.create_company.CreateCompanyFragment
import com.cuongpq.basemvvm.ui.employer.job.job_information.JobInformationFragment
import com.cuongpq.basemvvm.ui.employer.create_job.create_description.AddJobFragment
import com.cuongpq.basemvvm.ui.employer.create_job.create_question.CreateQuestionFragment
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.CreateRequestFragment
import com.cuongpq.basemvvm.ui.employer.create_job.create_right.CreateRightFragment
import com.cuongpq.basemvvm.ui.employer.create_job.create_status.CreateStatusFragment
import com.cuongpq.basemvvm.ui.employer.job.my_job.MyJobFragment
import com.cuongpq.basemvvm.ui.employer.company.update_company.UpdateCompanyFragment
import com.cuongpq.basemvvm.ui.login.signin.LoginFragment
import com.cuongpq.basemvvm.ui.login.signup.RegisterFragment
import com.cuongpq.basemvvm.ui.xample.user.UserFragment
import com.cuongpq.basemvvm.ui.noticification.NoticificationFragment
import com.cuongpq.basemvvm.ui.account.ProfileFragment
import com.cuongpq.basemvvm.ui.account.information.InformationFragment
import com.cuongpq.basemvvm.ui.account.update_skill.UpdateSkillFragment
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

    @ContributesAndroidInjector
    abstract fun contributeUpdateCompanyFragment() : UpdateCompanyFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateRequestFragment() : CreateRequestFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateQuestionFragment() : CreateQuestionFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateRightFragment() : CreateRightFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateStatusFragment() : CreateStatusFragment

    @ContributesAndroidInjector
    abstract fun contributeListJobSearchFragment() : ListJobSearchFragment

    @ContributesAndroidInjector
    abstract fun contributeAnswerFragment() : AnswerFragment

    @ContributesAndroidInjector
    abstract fun contributeCreateCompanyFragment() : CreateCompanyFragment

    @ContributesAndroidInjector
    abstract fun contributeInformationFragment() : InformationFragment
}