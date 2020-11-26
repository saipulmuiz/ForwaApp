package id.saipulmuiz.forwaapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventDetail(
    val id: String,
    val event_id: String,
    val nama_event: String?,
    val tgl_mulai: String?,
    val tgl_selesai: String?,
    val waktu_mulai: String?,
    val waktu_selesai: String?,
    val deskripsi_event: String?,
    val lokasi_event: String?,
    val img_event: String?,
    val event_created: String?,
    val created_by: String?,
    val photo: String?
) : Parcelable