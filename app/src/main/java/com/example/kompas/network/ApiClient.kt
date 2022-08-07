package com.example.kompas.network

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private const val TIMEOUT_LIMIT = 120L
        private const val BASE_URL = "http://app.sangsurya.net/sugitypm/api/"
//        private const val BASE_URL = "http://app.ragdalion.com/pickinglist/pickinglist_mobile/api/"
        private var retrofit: Retrofit? = null

        fun build(context: Context): Retrofit? {
            if (retrofit == null) {
                val gson = GsonBuilder().setLenient().create()

                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                val httpBuilder = OkHttpClient.Builder()
                    .addInterceptor(NetworkConnectionInterceptor(context))
                    .addInterceptor(logging)
                    .readTimeout(TIMEOUT_LIMIT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT_LIMIT, TimeUnit.SECONDS)

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpBuilder.build())
                    .build()

            }
            return retrofit
        }
    }
}