package com.example.kompas.data.api

import com.example.kompas.data.model.ResponseApi
import com.example.kompas.data.model.ResponsePostingan
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

interface ApiGetData {

    @GET("sementaraaja")
    fun getDataUser(): Call<ResponseApi>

    @GET("sementaraaja/1")
    fun getDataPostingan(
    ): Call<ResponsePostingan>
}