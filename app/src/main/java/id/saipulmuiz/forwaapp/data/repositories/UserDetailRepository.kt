package id.saipulmuiz.forwaapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.saipulmuiz.forwaapp.data.model.Status
import id.saipulmuiz.forwaapp.data.model.UserDetail
import id.saipulmuiz.forwaapp.data.network.api.ApiHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailRepository @Inject constructor(
    private val api: ApiHelper,
    private val userRepository: UserRepository
) {

    private val compositeDisposable = CompositeDisposable()

    //    Function : for get data detail user from api
    fun getDetail(username: String): LiveData<Status<UserDetail>> {
        val liveData = MutableLiveData<Status<UserDetail>>()

        compositeDisposable.add(
            api.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
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