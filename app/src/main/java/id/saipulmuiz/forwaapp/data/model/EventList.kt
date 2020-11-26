package id.saipulmuiz.forwaapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventList(
    val id: String,
    val event_id: String,
    val nama_event: String?,
    val tgl_mulai: String?,
    val tgl_selesai: String?,
    val deskripsi_event: String?,
    val lokasi_event: String?,
    val img_event: String?,
    val event_created: String?,
    val active_event: String
) : Parcelable