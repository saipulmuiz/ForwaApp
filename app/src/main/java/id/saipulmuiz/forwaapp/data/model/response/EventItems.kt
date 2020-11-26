package id.saipulmuiz.forwaapp.data.model.response

import id.saipulmuiz.forwaapp.data.model.EventList

data class EventItems(
    val items: List<EventList>,
    val total_count: Int
)