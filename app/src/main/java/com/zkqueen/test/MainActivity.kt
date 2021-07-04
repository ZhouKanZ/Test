package com.zkqueen.test

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mmapIdFirst = "first"
    val mmapIdSecond = "second"
    var dialog : MyDialog?=null
    var detector: GestureDetector?=null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initGestureListener()

        tv.setOnClickListener {
            dialog = dialog?: MyDialog(MainActivity@ this)
            dialog?.show()
        }

        // 今日未展示 且 不是正在展示
        tv.setBackgroundResource(if (false)
            R.drawable.anchor_dialog_tips_bg_dark else
                R.drawable.anchor_dialog_tips_bg_day)


//        tv.setTextColor(Color.parseColor("#ccffffff"))


        cl_main.setOnClickListener {
            Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show()
        }

        cl_main.setOnTouchListener { v, event ->
            detector!!.onTouchEvent(event)
        }

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    private fun initGestureListener() {
        val listener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                Log.d(TAG, "onFling: velocityX$velocityX --- velocityY$velocityY")
                return super.onFling(e1, e2, velocityX, velocityY)
            }
        }
        detector = object : GestureDetector(this, listener){}

    }

    companion object {
        private const val TAG = "MainActivity"
    }


}