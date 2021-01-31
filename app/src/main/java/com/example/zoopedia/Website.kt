package com.example.zoopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient

class Website : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website)
        this.getSupportActionBar()?.hide();

        var webView = findViewById<WebView>(R.id.wv_webview)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getStringExtra("url")!!)
    }
    
    fun close(view:View){
        finish()
    }
}