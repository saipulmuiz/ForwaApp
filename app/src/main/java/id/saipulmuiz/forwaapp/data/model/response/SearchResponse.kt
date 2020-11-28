package id.saipulmuiz.forwaapp.data.model.response

import id.saipulmuiz.forwaapp.data.model.UserSearch

// Data Class SearchResponse; Keyword : DataClass
data class SearchResponse(
    val total_count: Int,
    val page: String,
    val per_page: Int,
    val total_pages: Int,
    val items: ArrayList<UserSearch>
)