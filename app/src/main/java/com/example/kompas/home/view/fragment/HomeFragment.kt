package com.example.kompas.home.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.kompas.R
import com.example.kompas.home.view.AssesmentActivity
import com.example.kompas.home.view.WebviewContentActivity

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [HomeFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
class HomeFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNewsUpdate = view.findViewById<LinearLayout>(R.id.ll_newsupdate)
        val btnLiveOnTv = view.findViewById<LinearLayout>(R.id.ll_livetv)
        val btnLiveChat = view.findViewById<LinearLayout>(R.id.ll_livechat)
        val btnCareer = view.findViewById<LinearLayout>(R.id.ll_career)
        val btnAssesment = view.findViewById<LinearLayout>(R.id.ll_recruitment)

        btnLiveOnTv.setOnClickListener(){
            val intent = Intent(context, WebviewContentActivity::class.java)
            intent.putExtra("idButton", "2")
            startActivity(intent)

        }

        btnCareer.setOnClickListener(){
            val intent = Intent(context, WebviewContentActivity::class.java)
            intent.putExtra("idButton", "3")
            startActivity(intent)

        }

        btnAssesment.setOnClickListener(){
            val intent = Intent(context, AssesmentActivity::class.java)
            startActivity(intent)

        }

        btnLiveChat.setOnClickListener(){
            Toast.makeText(context, "Sorry that feature unavailable", Toast.LENGTH_SHORT).show()

        }

        btnNewsUpdate.setOnClickListener(){
            val intent = Intent(context, WebviewContentActivity::class.java)
            intent.putExtra("idButton", "1")
            startActivity(intent)

        }
    }
}