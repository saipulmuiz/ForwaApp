package id.saipulmuiz.forwaapp.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import id.saipulmuiz.forwaapp.data.repositories.UserRepository
import id.saipulmuiz.forwaapp.util.SP_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// App Module; Keyword : Dagger2
@Module
object AppModule {

    /* --- API --- */
    @Singleton
    @JvmStatic
    @Provides
    fun provideApiHelper(): ApiHelper = ApiHelper.create()

    @Singleton
    @JvmStatic
    @Provides
    fun provideUserRepository() = UserRepository()

    @Singleton
    @JvmStatic
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
}