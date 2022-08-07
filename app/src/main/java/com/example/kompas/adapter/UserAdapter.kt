package com.example.kompas.adapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.kompas.R
import com.example.kompas.Utility
import com.example.kompas.data.model.DataPengguna
import com.example.kompas.data.model.DataPostingan
import com.example.kompas.data.model.InterfaceData
import com.example.kompas.home.view.ProfileAssesmentActivity
import com.example.kompas.home.view.WebviewContentActivity
import kotlinx.android.synthetic.main.item_people.view.*

class UserAdapter(
    private val list: ArrayList<DataPengguna>,
    private val listPostingan : ArrayList<DataPostingan>,
    private val sessionPost : Utility,

) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(user: DataPengguna) {
            with(itemView) {
                tvDatanama.text = user.namaUser
                val idUser = user.idUser

                if (user.idUser == "1") {
                    iv_datafoto.setImageResource(R.drawable.peterparker)
                } else if (user.idUser == "2") {
                    iv_datafoto.setImageResource(R.drawable.peterparker2)
                } else if (user.idUser == "3") {
                    iv_datafoto.setImageResource(R.drawable.peterparker3)
                } else {
                    iv_datafoto.setImageResource(R.drawable.peterparker)
                }


                itemView.setOnClickListener() {
                    listPostingan.clear()
                    listPostingan.addAll(user.postingan)



                    val intent = Intent(context, ProfileAssesmentActivity::class.java)
                    intent.putExtra("IdUser", user.idUser)
                    intent.putExtra("NamaUser", user.namaUser)
                    intent.putExtra("SocialMedia", user.sosialMedia)
                    intent.putExtra("JabatanUser", user.jabatanUser)
                    intent.putExtra("MotoHidup", user.motoHidup)
                    context.startActivity(intent)
                }
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_people, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bind(list[position])
    }



    override fun getItemCount(): Int = list.size




}