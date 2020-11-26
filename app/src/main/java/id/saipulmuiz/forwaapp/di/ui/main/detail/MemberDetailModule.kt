package id.saipulmuiz.forwaapp.di.ui.main.detail

import dagger.Module
import dagger.Provides
import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import id.saipulmuiz.forwaapp.data.repositories.UserDetailRepository
import id.saipulmuiz.forwaapp.data.repositories.UserRepository
import javax.inject.Singleton

//Main - Detail Module; Keyword : Dagger2
@Module
object MemberDetailModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideMemberDetailRepository(
        api: ApiHelper,
        userRepository: UserRepository
    ) = UserDetailRepository(api, userRepository)
}