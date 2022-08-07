package com.example.kompas.data.model

import com.google.gson.annotations.SerializedName

data class DataPostingan(
    @SerializedName("judul")
    var judul: String,
    @SerializedName("deskripsi")
    var deskripsi: String,
    @SerializedName("bintang")
    var bintang: String,
    @SerializedName("waktu")
    var waktu: String,
)
