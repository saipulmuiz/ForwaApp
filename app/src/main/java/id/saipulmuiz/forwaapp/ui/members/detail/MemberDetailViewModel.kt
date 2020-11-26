package id.saipulmuiz.forwaapp.ui.members.detail

import androidx.lifecycle.ViewModel
import id.saipulmuiz.forwaapp.data.repositories.UserDetailRepository
import javax.inject.Inject

class MemberDetailViewModel @Inject constructor(
    private val repository: UserDetailRepository
) : ViewModel() {

//    Function : For get data user detail from api
    fun getDetail(username: String) = repository.getDetail(username)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}