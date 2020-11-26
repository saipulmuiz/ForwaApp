package id.saipulmuiz.forwaapp.di.app

import id.saipulmuiz.forwaapp.di.ui.main.MainActivityBuilderModule
import id.saipulmuiz.forwaapp.di.ui.main.MainModule
import id.saipulmuiz.forwaapp.di.ui.main.MainViewModelModule
import id.saipulmuiz.forwaapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Activity Builder Module; Keyword : Dagger2
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityBuilderModule::class,
            MainViewModelModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}