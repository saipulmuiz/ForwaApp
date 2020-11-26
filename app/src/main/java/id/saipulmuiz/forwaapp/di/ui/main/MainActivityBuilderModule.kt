package id.saipulmuiz.forwaapp.di.ui.main

import id.saipulmuiz.forwaapp.di.ui.main.home.HomeModule
import id.saipulmuiz.forwaapp.di.ui.main.setting.SettingModule
import id.saipulmuiz.forwaapp.ui.members.member.MemberFragment
import id.saipulmuiz.forwaapp.ui.setting.SettingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.saipulmuiz.forwaapp.di.*
import id.saipulmuiz.forwaapp.di.ui.main.dashboard.DashboardModule
import id.saipulmuiz.forwaapp.di.ui.main.detail.EventDetailModule
import id.saipulmuiz.forwaapp.di.ui.main.detail.MemberDetailModule
import id.saipulmuiz.forwaapp.di.ui.main.event.EventModule
import id.saipulmuiz.forwaapp.di.ui.main.notifications.NotificationsModule
import id.saipulmuiz.forwaapp.ui.dashboard.DashboardFragment
import id.saipulmuiz.forwaapp.ui.event.EventFragment
import id.saipulmuiz.forwaapp.ui.event.detail.EventDetailFragment
import id.saipulmuiz.forwaapp.ui.members.detail.MemberDetailFragment
import id.saipulmuiz.forwaapp.ui.notifications.NotificationsFragment

// Main Activity Builder Module; Keyword : Dagger2
@Module
abstract class MainActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(
        modules = [
            HomeModule::class
        ]
    )
    abstract fun contributeHomeFragment(): MemberFragment

    @SettingScope
    @ContributesAndroidInjector(
        modules = [
            SettingModule::class
        ]
    )
    abstract fun contributeSettingFragment(): SettingFragment

    @DashboardScope
    @ContributesAndroidInjector(
        modules = [
            DashboardModule::class
        ]
    )
    abstract fun contributeDashboardFragment(): DashboardFragment

    @EventScope
    @ContributesAndroidInjector(
        modules = [
            EventModule::class
        ]
    )
    abstract fun contributeEventFragment(): EventFragment

    @NotificationsScope
    @ContributesAndroidInjector(
        modules = [
            NotificationsModule::class
        ]
    )
    abstract fun contributeNotificationsFragment(): NotificationsFragment

    @MemberDetailScope
    @ContributesAndroidInjector(
        modules = [
            MemberDetailModule::class]
    )
    abstract fun contributeMemberDetailFragment(): MemberDetailFragment

    @EventDetailScope
    @ContributesAndroidInjector(
        modules = [
        EventDetailModule::class]
    )
    abstract fun contributeEventDetailFragment(): EventDetailFragment
}