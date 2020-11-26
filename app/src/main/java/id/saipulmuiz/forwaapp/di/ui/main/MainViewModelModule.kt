package id.saipulmuiz.forwaapp.di.ui.main

import androidx.lifecycle.ViewModel
import id.saipulmuiz.forwaapp.di.viewmodel.ViewModelKey
import id.saipulmuiz.forwaapp.ui.MainViewModel
import id.saipulmuiz.forwaapp.ui.members.member.MemberViewModel
import id.saipulmuiz.forwaapp.ui.setting.SettingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.saipulmuiz.forwaapp.ui.dashboard.DashboardViewModel
import id.saipulmuiz.forwaapp.ui.event.EventViewModel
import id.saipulmuiz.forwaapp.ui.event.detail.EventDetailViewModel
import id.saipulmuiz.forwaapp.ui.members.detail.MemberDetailViewModel
import id.saipulmuiz.forwaapp.ui.notifications.NotificationsViewModel

// Main View Model Module; Keyword : Dagger2
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MemberViewModel::class)
    abstract fun bindHomeViewModel(memberViewModel: MemberViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(settingViewModel: SettingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    abstract fun bindEventViewModel(eventViewModel: EventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(notificationsViewModel: NotificationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MemberDetailViewModel::class)
    abstract fun bindMemberDetailViewModel(detailMemberViewModel: MemberDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailViewModel::class)
    abstract fun bindEventDetailViewModel(detaiEventViewModel: EventDetailViewModel): ViewModel
}