package com.cuongpq.basemvvm.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cuongpq.basemvvm.di.model.ViewModelFactory
import com.cuongpq.basemvvm.di.model.ViewModelKey
import com.cuongpq.basemvvm.ui.candidate.CandidateMainModelView
import com.cuongpq.basemvvm.ui.candidate.job_information.JobInformationViewModel
import com.cuongpq.basemvvm.ui.employer.EmployerMainViewModel
import com.cuongpq.basemvvm.ui.employer.add_job.AddJobViewModel
import com.cuongpq.basemvvm.ui.employer.myjob.MyJobViewModel
import com.cuongpq.basemvvm.ui.login.FirstViewModel
import com.cuongpq.basemvvm.ui.login.signin.LoginViewModel
import com.cuongpq.basemvvm.ui.login.signup.RegisterViewModel
import com.cuongpq.basemvvm.ui.main.MainViewModel
import com.cuongpq.basemvvm.ui.noticification.NotificationViewModel
import com.cuongpq.basemvvm.ui.profile.ProfileViewModel
import com.cuongpq.basemvvm.ui.profile.update_skill.UpdateSkillViewModel
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
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}