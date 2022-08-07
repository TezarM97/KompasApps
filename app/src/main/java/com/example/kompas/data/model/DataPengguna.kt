package com.example.kompas.data.model

import com.google.gson.annotations.SerializedName

data class DataPengguna(
    @SerializedName("user_id")
    var idUser: String,
    @SerializedName("nama")
    var namaUser: String,
    @SerializedName("sosial_media")
    var sosialMedia: String,
    @SerializedName("jabatan")
    var jabatanUser: String,
    @SerializedName("moto_hidup")
    var motoHidup: String,
    @SerializedName("postingan")
    val postingan: Array<DataPostingan>

)
