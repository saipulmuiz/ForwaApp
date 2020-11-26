package id.saipulmuiz.forwaapp.di.ui.main.detail

import dagger.Module
import dagger.Provides
import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import id.saipulmuiz.forwaapp.data.repositories.EventDetailRepository
import id.saipulmuiz.forwaapp.data.repositories.EventRepository
import javax.inject.Singleton

@Module
object EventDetailModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideEventDetailRepository(
        api: ApiHelper,
        eventRepository: EventRepository
    ) = EventDetailRepository(api, eventRepository)
}