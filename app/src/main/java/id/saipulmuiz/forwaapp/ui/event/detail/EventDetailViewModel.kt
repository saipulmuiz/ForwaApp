package id.saipulmuiz.forwaapp.ui.event.detail

import androidx.lifecycle.ViewModel
import id.saipulmuiz.forwaapp.data.repositories.EventDetailRepository
import javax.inject.Inject

class EventDetailViewModel @Inject constructor(
    private val repository: EventDetailRepository
) : ViewModel() {

    fun getDetail(eventId: String) = repository.getDetail(eventId)

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}