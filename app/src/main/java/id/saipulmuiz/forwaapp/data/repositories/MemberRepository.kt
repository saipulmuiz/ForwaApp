package id.saipulmuiz.forwaapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.data.model.response.SearchResponse
import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

// Home repository; Keyword : Repository
class MemberRepository @Inject constructor(
    private val api: ApiHelper
) {

    private val compositeDisposable = CompositeDisposable()

    // Function : for get data list search user from api
    fun getUserSearch(keyword: String, pages: String): LiveData<Status<SearchResponse>> {
        val liveData = MutableLiveData<Status<SearchResponse>>()

        compositeDisposable.add(
            api.getSearchUser(keyword, pages)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        it?.let {
                            liveData.postValue(Status.success(it))
                            return@subscribeBy
                        }
                        liveData.postValue(Status.error("Error", null))
                    },
                    onError = {
                        liveData.postValue(Status.error("Error : ${it.message}", null))
                    }
                )
        )

        return liveData
    }

    // Function : for dispose home repository composite
    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}