package id.saipulmuiz.forwaapp.di

import javax.inject.Scope

// Custom Scope; Keyword : Dagger2
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SettingScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DashboardScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class EventScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class NotificationsScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class MemberDetailScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class EventDetailScope