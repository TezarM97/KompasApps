package com.example.kompas.data.model

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class ResponsePostingan(
    @SerializedName("status") val response: String,
    @SerializedName("data") val data: JsonElement,
    @SerializedName("postingan") val postingan: ArrayList<DataPostingan>
)
