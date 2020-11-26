package id.saipulmuiz.forwaapp.data.network.api

import id.saipulmuiz.forwaapp.data.model.EventDetail
import id.saipulmuiz.forwaapp.data.model.EventList
import id.saipulmuiz.forwaapp.data.model.UserDetail
import id.saipulmuiz.forwaapp.data.model.response.EventItems
import id.saipulmuiz.forwaapp.data.model.response.SearchResponse
import id.saipulmuiz.forwaapp.data.network.setting.BasicInterceptor
import id.saipulmuiz.forwaapp.util.ACCESS_TOKEN
import id.saipulmuiz.forwaapp.util.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Api Interface; Keyword : Retrofit2
interface ApiHelper {

    companion object {
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        // Function : for configure retrofit and return ApiHelper
        fun create(): ApiHelper {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(BasicInterceptor(ACCESS_TOKEN))
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
                .build()
                .create(ApiHelper::class.java)
        }
    }

    /* --- User --- */
    @GET("search/users")
    fun getSearchUser(
        @Query("q") keyword: String
    ): Observable<SearchResponse>

    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Observable<UserDetail>

    @GET("event/")
    fun getEventList(
    ): Observable<EventItems>

    @GET("event/{eventid}/")
    fun getEvent(
        @Path("eventid") event_id: String
    ): Observable<EventDetail>
}