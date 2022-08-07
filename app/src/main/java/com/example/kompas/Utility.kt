package com.example.kompas

import android.content.Context
import com.example.kompas.data.model.DataPostingan

class Utility(context: Context) {
    private val JUDUL = "judul"
    private val DESKRIPSI = "deskripsi"
    private val BINTANG = "bintang"
    private val WAKTU = "waktu"

    private val SHARE_PREF_POST = "sharedpref_post"
    private val sharedPreferences = context.getSharedPreferences(SHARE_PREF_POST, Context.MODE_PRIVATE)

    fun setPostingan (judul: String,deskripsi: String,bintang: String,waktu: String,){
        val editor = sharedPreferences.edit()
        editor.putString(JUDUL, judul)
        editor.putString(DESKRIPSI, deskripsi)
        editor.putString(BINTANG, bintang)
        editor.putString(WAKTU, waktu)

    }
    fun getUser(): DataPostingan {
        return DataPostingan(
            sharedPreferences.getString(JUDUL, null) ?: "",
            sharedPreferences.getString(DESKRIPSI, null) ?: "",
            sharedPreferences.getString(BINTANG, null) ?: "",
            sharedPreferences.getString(WAKTU, null) ?: ""
        )
    }
}