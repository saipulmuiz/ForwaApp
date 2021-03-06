package id.saipulmuiz.forwaapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.data.model.response.EventItems
import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val api: ApiHelper
) {
    private val compositeDisposable = CompositeDisposable()

    //    Function : for get data list event from api
    fun getEventList(): LiveData<Status<EventItems>> {
        val liveData = MutableLiveData<Status<EventItems>>()

        compositeDisposable.add(
            api.getEventList()
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

    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}