package com.zkqueen.test

import android.app.Dialog
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_test.*
import java.io.IOException
import java.io.InputStream
import java.net.URL

class MyDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_test)
        window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#66000000")))
        window?.attributes?.let {
            it.height = ScreenUtils.dp2px(context, 110f)
            it.width = ViewGroup.LayoutParams.MATCH_PARENT
            it.dimAmount = 0.4f
            it.gravity = Gravity.BOTTOM
        }
        Log.d(TAG, "onCreate: ${context.filesDir.absolutePath}")
//        tv_bottom.setOnClickListener {
//            initBitmap()
//        }
    }

    private fun initBitmap() {
        Thread {
            val url: URL
            var iStream: InputStream? = null
            val options = BitmapFactory.Options()
            try {
                url = URL("https://ceph-dev-pub.dz11.com/fed-doc/1619016961175.png")
                iStream = url.openConnection().getInputStream()
                options.inJustDecodeBounds = true
                BitmapFactory.decodeStream(iStream, null, options)

                val b =  BitmapFactory.decodeStream(iStream)
                Log.d(TAG, "initBitmap: $b")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (iStream != null) {
                    try {
                        iStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }.start()
    }

    override fun show() {
        super.show()
        Log.d(TAG, "show: ")
    }

    companion object {
        private const val TAG = "MyDialog"
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow: ")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow: ")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d(TAG, "onWindowFocusChanged: $hasFocus ${Thread.currentThread().name}")

    }

}