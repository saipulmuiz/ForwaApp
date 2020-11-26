package id.saipulmuiz.forwaapp.ui.members.member

import android.util.Log
import androidx.lifecycle.ViewModel
import id.saipulmuiz.forwaapp.data.repositories.MemberRepository
import javax.inject.Inject

class MemberViewModel @Inject constructor(
    private val repository: MemberRepository
) : ViewModel() {

    // Function : for get data user search from API
    fun getUserSearch(keyword: String) = repository.getUserSearch(keyword)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}
