package com.example.kompas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.kompas.R
import com.example.kompas.data.model.DataPostingan
import kotlinx.android.synthetic.main.item_postingan.view.*

class PostinganAdapter (private val list: ArrayList<DataPostingan>):RecyclerView.Adapter<PostinganAdapter.PostinganViewHolder>() {
    inner class PostinganViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind (post: DataPostingan){
            with(itemView){
                tv_judul.text = post.judul
                tv_deskripsi.text = post.deskripsi
                tv_bintang.text = post.bintang
                tv_waktu.text = post.waktu
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostinganViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_postingan, parent, false)
        return PostinganViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostinganViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}