package com.example.kompas.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kompas.R
import com.example.kompas.adapter.PostinganAdapter
import com.example.kompas.adapter.UserAdapter
import com.example.kompas.data.api.ApiGetData
import com.example.kompas.data.model.DataPengguna
import com.example.kompas.data.model.DataPostingan
import com.example.kompas.data.model.ResponseApi
import com.example.kompas.data.model.ResponsePostingan
import com.example.kompas.network.ApiClient
import com.example.kompas.network.NoNetworkException
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_assesment.*
import kotlinx.android.synthetic.main.activity_profile_assesment.*
import kotlinx.android.synthetic.main.item_people.view.*
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class ProfileAssesmentActivity : AppCompatActivity() {

    private lateinit var idUserString: String
    private var list = ArrayList<DataPengguna>()
    private var listCoba = ArrayList<DataPostingan>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_assesment)

        val stringId = intent.getStringExtra("IdUser")
        val stringNama = intent.getStringExtra("NamaUser")
        val stringSosmed = intent.getStringExtra("SocialMedia")
        val stringJabatan = intent.getStringExtra("JabatanUser")
        val stringMoto = intent.getStringExtra("MotoHidup")





        tv_idUser.setText(stringId)

        tv_namaprofile.setText(stringNama)
        tv_socialmedia.setText(stringSosmed)
        tv_dialoguser.setText("$stringJabatan, $stringMoto")


        if (stringId == "1") {
            iv_datafotouser.setImageResource(R.drawable.peterparker)
        } else if (stringId == "2") {
            iv_datafotouser.setImageResource(R.drawable.peterparker2)
        } else if (stringId == "3") {
            iv_datafotouser.setImageResource(R.drawable.peterparker3)
        } else {
            iv_datafotouser.setImageResource(R.drawable.peterparker)
        }


        btn_backprofile.setOnClickListener() {
            finish()
        }

        idUserString = tv_idUser.toString()

        getDataPostingan(idUserString)

    }

    private fun getDataPostingan(idUser: String) {

        rv_postingan.setHasFixedSize(true)
        rv_postingan.layoutManager = LinearLayoutManager(this)

        val api = ApiClient.build(this)?.create(ApiGetData::class.java)
        val endpoint = api!!.getDataUser()
        endpoint.enqueue(object : retrofit2.Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                response.body()?.let {
                    if (response.body()!!.response == "200") {
                        val listResponse = response.body()?.data

                        listResponse?.let { it1 -> list.addAll(it1) }


                        if (tv_idUser.text.equals("1")) {
                            val listPostingan = response.body()!!.data.get(0).postingan
                            listPostingan?.let { it2 -> listCoba.addAll(it2) }
                        } else if (tv_idUser.text.equals("2")) {
                            val listPostingan = response.body()!!.data.get(1).postingan
                            listPostingan?.let { it2 -> listCoba.addAll(it2) }
                        } else {
                            val listPostingan = response.body()!!.data.get(2).postingan
                            listPostingan?.let { it2 -> listCoba.addAll(it2) }
                        }




                        Log.d("Fine", "Success = " + tv_idUser.text)

                        if (!listCoba.isEmpty()) {
                            val adapter = PostinganAdapter(listCoba)
                            rv_postingan.adapter = adapter

                        } else {
                            Toast.makeText(
                                this@ProfileAssesmentActivity,
                                "Data Status Not Found",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                        }


                    } else {
                        Toast.makeText(
                            this@ProfileAssesmentActivity,
                            "Connection Error, Please try again.",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Toast.makeText(
                    this@ProfileAssesmentActivity,
                    if (t is NoNetworkException) t.message else "Connection Error, Please try again.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })


    }


}