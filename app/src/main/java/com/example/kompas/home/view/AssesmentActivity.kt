package com.example.kompas.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.widget.*
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kompas.R
import com.example.kompas.Utility
import com.example.kompas.adapter.UserAdapter
import com.example.kompas.data.api.ApiGetData
import com.example.kompas.data.model.DataPengguna
import com.example.kompas.data.model.DataPostingan
import com.example.kompas.data.model.ResponseApi
import com.example.kompas.network.ApiClient
import com.example.kompas.network.NoNetworkException
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_assesment.*
import kotlinx.android.synthetic.main.item_people.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception
import javax.security.auth.callback.Callback

class AssesmentActivity : AppCompatActivity() {

    private var list = ArrayList<DataPengguna>()
    private var listPostingan = ArrayList<DataPostingan>()
    private lateinit var utilty: Utility
    private var listFilterd = ArrayList<DataPengguna>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assesment)

        utilty = Utility(this)


        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener() {
            finish()
        }

        getData()
        swiperefresh.setOnRefreshListener() {
            getData()
        }


    }


    private fun getData() {
        swiperefresh.isRefreshing = true
        rv_dataorang.setHasFixedSize(true)
        rv_dataorang.layoutManager = LinearLayoutManager(this)


        val api = ApiClient.build(this)?.create(ApiGetData::class.java)
        val endpoint = api!!.getDataUser()

        endpoint.enqueue(object : retrofit2.Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                response.body()?.let {
                    if (response.body()!!.response == "200") {
                        val listResponse = response.body()?.data
//                        val dataObjectNama = response.body()!!.dataObject.asJsonObject
//                        val namaUserObject = dataObjectNama["nama"].asString
//
//                        Log.d("Data Nama People", "onResponse: " +namaUserObject)
                        list.clear()

                        listResponse?.let { it1 -> list.addAll(it1) }
                        val adapter = UserAdapter(list, listPostingan, utilty)
                        rv_dataorang.adapter = adapter
                        swiperefresh.isRefreshing = false
                        filterFunction(adapter, list)
                        listResponse


//                        Log.d("Data Profile", "From Get: " + list[].postingan.get())


                    } else {
                        Toast.makeText(
                            this@AssesmentActivity,
                            "Connection Error, Please try again.",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        swiperefresh.isRefreshing = false
                    }
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Toast.makeText(
                    this@AssesmentActivity,
                    if (t is NoNetworkException) t.message else "Connection Error, Please try again.",
                    Toast.LENGTH_SHORT
                )
                    .show()
                swiperefresh.isRefreshing = false
            }
        })


    }

    private fun filterFunction(adapter: UserAdapter, list: ArrayList<DataPengguna>) {
        search_view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isEmpty()){
                    getData()
                } else {
                  try {
                      if (p0.toString().equals(list.get(0).namaUser)) {
                          list.clear()
                          getDataFilter(list, p0.toString())

                      } else if (p0.toString().equals(list.get(1).namaUser)) {
                          list.clear()
                          getDataFilter(list, p0.toString())

                      } else if (p0.toString().equals(list.get(2).namaUser)) {
                          list.clear()
                          getDataFilter(list, p0.toString())

                      }
                  } catch (e: Exception){
                      Log.d("Data Error", "Error: "+e)
                  }
                }
            }
        })


    }

    private fun getDataFilter(list1: ArrayList<DataPengguna>, toString: String) {
        swiperefresh.isRefreshing = true
        val api = ApiClient.build(this)?.create(ApiGetData::class.java)
        val endpoint = api!!.getDataUser()

        endpoint.enqueue(object : retrofit2.Callback<ResponseApi> {
            override fun onResponse(call: Call<ResponseApi>, response: Response<ResponseApi>) {
                response.body()?.let {
                    if (response.body()!!.response == "200") {
                        list.clear()


                        val listResponse = response.body()?.data
                        listResponse?.let { it1 -> list.addAll(it1) }
                        val adapter = UserAdapter(listFilterd, listPostingan, utilty)
                        rv_dataorang.adapter = adapter

                        if (toString.equals(list1.get(0).namaUser)) {
                            listFilterd.clear()
                            listFilterd.add(list1.get(0))
                        } else if (toString.equals(list1.get(1).namaUser)) {
                            listFilterd.clear()
                            listFilterd.add(list1.get(1))
                        } else if (toString.equals(list1.get(2).namaUser)) {
                            listFilterd.clear()
                            listFilterd.add(list1.get(2))
                        } else {
                            Toast.makeText(
                                this@AssesmentActivity,
                                "Data not found, Please try again.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        swiperefresh.isRefreshing = false

                    } else {
                        Toast.makeText(
                            this@AssesmentActivity,
                            "Connection Error, Please try again.",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        swiperefresh.isRefreshing = false
                    }
                }
            }

            override fun onFailure(call: Call<ResponseApi>, t: Throwable) {
                Toast.makeText(
                    this@AssesmentActivity,
                    if (t is NoNetworkException) t.message else "Connection Error, Please try again.",
                    Toast.LENGTH_SHORT
                )
                    .show()
                swiperefresh.isRefreshing = false
            }
        })
    }


}