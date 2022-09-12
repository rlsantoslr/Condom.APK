package com.fiap.webviewkotlin

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var webView: WebView = TODO()
    private var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (webView != null) {
            val webSettings = webView.settings
            webSettings.javaScriptEnabled = true

            webView.webViewClient = WebViewClient()
            webView.webChromeClient = WebChromeClient()
            webView.loadUrl( "http://www.google.com")

            webView.webViewClient = object: WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    progressBar.visibility = View.VISIBLE
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    progressBar.visibility = View.GONE
                    super.onPageFinished(view, url)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        }
        else {
            super.onBackPressed()
        }
    }
}