package com.example.kompas.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.kompas.R

class WebviewContentActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_content)
        val tvNameMenu = findViewById<TextView>(R.id.tv_content)
        val btnBack = findViewById<Button>(R.id.btn_back)
        webView = findViewById(R.id.webview_content)
        webView.settings.setJavaScriptEnabled(true)








        btnBack.setOnClickListener(){
            finish()
        }


        val stringId  = intent.getStringExtra("idButton")
        Log.d("WebviewContentActivity", "onCreate: Data String = "+stringId)

        if (stringId == "1"){
            tvNameMenu.setText("News Update")

           LoadingBar()

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true

                }

            }

            webView.loadUrl("https://www.kompas.id/")



        } else if(stringId == "2"){
            tvNameMenu.setText("Live On Tv")
            LoadingBar()
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }
            webView.loadUrl("https://www.kompas.tv/live")
        } else if(stringId == "3"){
            tvNameMenu.setText("Career")
            LoadingBar()
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }
            webView.loadUrl("https://karier.kompas.id/")

        }
    }

    fun LoadingBar(){
        val builder = AlertDialog.Builder(this,R.style.MyAlertDialogStyle)
        val itemView = LayoutInflater.from(this).inflate(R.layout.layout_customdialog,null)
        builder.setView(itemView)
        val dialog = builder.create()
        object : CountDownTimer(1500, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                dialog.show()
            }

            override fun onFinish() {
                dialog.dismiss()
            }
        }.start()
    }


}