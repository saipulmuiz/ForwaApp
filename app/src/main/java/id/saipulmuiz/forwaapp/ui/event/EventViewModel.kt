package id.saipulmuiz.forwaapp.ui.event

import androidx.lifecycle.ViewModel
import id.saipulmuiz.forwaapp.data.repositories.EventRepository
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    //    Function : for get data event from API
    fun getEventList() = repository.getEventList()

    override fun onCleared() {
        repository.disposeComposite()
        super.onCleared()
    }
}