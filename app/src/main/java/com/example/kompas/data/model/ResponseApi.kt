package com.example.kompas.data.model

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("status") val response: String,
    @SerializedName("data") val data: ArrayList<DataPengguna>,
    @SerializedName("postingan") val postingan: ArrayList<DataPostingan>,
//    @SerializedName("data") val dataObject: JsonArray,


    )
