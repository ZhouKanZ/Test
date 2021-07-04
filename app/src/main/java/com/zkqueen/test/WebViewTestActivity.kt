package com.zkqueen.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview_test.*

/**
 *  @author: yifeng
 *  @date: 2021/7/1
 *  @version:
 *  @desc:
 */
class WebViewTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test)

        initWebViewSetting()
        webview.loadUrl("http://app.nxnews.net/ningxia/cfwz/zt/201708/t20170807_1859270.html")

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSetting() {
        webview.settings?.let {
            it.javaScriptEnabled = true
            it.useWideViewPort = true
        }

        webview.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
            }
        }

        webview.addJavascriptInterface(AppInterface(), "AndroidFunction")
    }

    companion object {
        private const val TAG = "WebViewTestActivity"
    }

     open class AppInterface{

        @JavascriptInterface
        fun resize(height: Float){

        }

    }

}