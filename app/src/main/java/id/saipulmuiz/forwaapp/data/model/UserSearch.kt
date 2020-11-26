package id.saipulmuiz.forwaapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Data Class User Search; Keyword : DataClass
@Parcelize
data class UserSearch(
    val alamat: String?,
    val email: String?,
    val gender: String?,
    val id: String?,
    val nama: String?,
    val no_induk: String?,
    val phone: String,
    val role: String?,
    val photo: String?,
    val tempat_lahir: String?,
    val tgl_lahir: String?,
    val user_created: Int?,
    val username: String
) : Parcelable