package com.example.account_project_evaluation.Activity

import android.os.Bundle
import android.print.PrintManager
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.account_project_evaluation.R
import com.example.account_project_evaluation.databinding.ActivityWebviewBinding


class WebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        val url=intent.getStringExtra("invoiceUrl")
        binding.webview.loadUrl(url!!)

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.setSupportZoom(true)
        binding.webview.settings.setAllowContentAccess(true)
        binding.webview.settings.setAllowFileAccess(true)
        binding.webview.settings.setDatabaseEnabled(true)
        binding.webview.settings.setDomStorageEnabled(true)

        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // Code to execute when page starts loading
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Code to execute when page finishes loading
            }
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                // Code to handle error
            }
        }

        binding.fbPrint.setOnClickListener {
            createWebPagePrint(binding.webview)
        }

        binding.webview.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String,
                result: JsResult
            ): Boolean {
                return if (message == "abc") {
                    result.cancel()
                    true
                } else false
            }
        })
    }
    private fun createWebPagePrint(webView: WebView) {
        val printManager = getSystemService(PRINT_SERVICE) as PrintManager
        val jobName = "${getString(R.string.app_name)} Document"

        val printAdapter = webView.createPrintDocumentAdapter(jobName)
        printManager.print(jobName, printAdapter, null)
    }

    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (binding.webview.canGoBack())
            binding.webview.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}