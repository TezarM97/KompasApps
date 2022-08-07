package com.example.kompas.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Postingan(
    val dataJudul : String,
    val dataDeskripsi: String,
    val dataBintang : String,
    val dataWaktu : String

) :Parcelable
