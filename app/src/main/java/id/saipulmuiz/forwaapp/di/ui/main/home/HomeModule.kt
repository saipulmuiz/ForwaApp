package id.saipulmuiz.forwaapp.di.ui.main.home

import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import id.saipulmuiz.forwaapp.data.repositories.MemberRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Main - Home Module; Keyword : Dagger2
@Module
object HomeModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideHomeRepository(
        api: ApiHelper
    ) = MemberRepository(api)
}