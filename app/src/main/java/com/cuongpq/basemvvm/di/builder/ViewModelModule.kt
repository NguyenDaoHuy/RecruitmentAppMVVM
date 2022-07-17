package com.cuongpq.basemvvm.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cuongpq.basemvvm.di.model.ViewModelFactory
import com.cuongpq.basemvvm.di.model.ViewModelKey
import com.cuongpq.basemvvm.ui.candidate.CandidateMainModelView
import com.cuongpq.basemvvm.ui.candidate.answer.AnswerViewModel
import com.cuongpq.basemvvm.ui.candidate.list_job.ListJobSearchViewModel
import com.cuongpq.basemvvm.ui.employer.job.job_information.JobInformationViewModel
import com.cuongpq.basemvvm.ui.employer.EmployerMainViewModel
import com.cuongpq.basemvvm.ui.employer.company.create_company.CreateCompanyViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_description.AddJobViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_question.CreateQuestionViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_request.CreateRequestViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_right.CreateRightViewModel
import com.cuongpq.basemvvm.ui.employer.create_job.create_status.CreateStatusViewModel
import com.cuongpq.basemvvm.ui.employer.job.my_job.MyJobViewModel
import com.cuongpq.basemvvm.ui.employer.company.update_company.UpdateCompanyViewModel
import com.cuongpq.basemvvm.ui.login.FirstViewModel
import com.cuongpq.basemvvm.ui.login.signin.LoginViewModel
import com.cuongpq.basemvvm.ui.login.signup.RegisterViewModel
import com.cuongpq.basemvvm.ui.xample.MainViewModel
import com.cuongpq.basemvvm.ui.noticification.NotificationViewModel
import com.cuongpq.basemvvm.ui.account.ProfileViewModel
import com.cuongpq.basemvvm.ui.account.information.InformationViewModel
import com.cuongpq.basemvvm.ui.account.update_skill.UpdateSkillViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployerMainViewModel::class)
    abstract fun bindsEmployerMainViewModel(employerMainViewModel: EmployerMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CandidateMainModelView::class)
    abstract fun bindsCandidateMainViewModel(candidateMainModelView: CandidateMainModelView): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    abstract fun bindsFirstViewModel(firstViewModel: FirstViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindsLoginViewModel(logInViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindsRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindsProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyJobViewModel::class)
    abstract fun bindsMyJobViewModel(myJobViewModel: MyJobViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindsNotificationViewModel(notificationViewModel: NotificationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddJobViewModel::class)
    abstract fun bindsAddJobViewModel(addJobViewModel: AddJobViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateSkillViewModel::class)
    abstract fun bindsUpdateSkillViewModel(updateSkillViewModel: UpdateSkillViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JobInformationViewModel::class)
    abstract fun bindsJobInformationViewModel(jobInformationViewModel: JobInformationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateCompanyViewModel::class)
    abstract fun bindsUpdateCompanyViewModel(updateCompanyViewModel: UpdateCompanyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateRequestViewModel::class)
    abstract fun bindsCreateRequestViewModel(createRequestViewModel: CreateRequestViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateQuestionViewModel::class)
    abstract fun bindsCreateQuestionViewModel(createQuestionViewModel: CreateQuestionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateRightViewModel::class)
    abstract fun bindsCreateRightViewModel(createRightViewModel: CreateRightViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateStatusViewModel::class)
    abstract fun bindsCreateStatusViewModel(createStatusViewModel: CreateStatusViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListJobSearchViewModel::class)
    abstract fun bindsListJobSearchViewModel(listJobSearchViewModel: ListJobSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnswerViewModel::class)
    abstract fun bindsAnswerViewModel(answerViewModel: AnswerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateCompanyViewModel::class)
    abstract fun bindsCreateCompanyViewModel(createCompanyViewModel: CreateCompanyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InformationViewModel::class)
    abstract fun bindsInformationViewModel(informationViewModel: InformationViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}