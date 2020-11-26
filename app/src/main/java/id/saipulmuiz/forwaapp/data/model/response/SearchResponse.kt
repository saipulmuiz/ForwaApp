package id.saipulmuiz.forwaapp.data.model.response

import id.saipulmuiz.forwaapp.data.model.UserSearch

// Data Class SearchResponse; Keyword : DataClass
data class SearchResponse(
    val items: List<UserSearch>,
    val total_count: Int
)