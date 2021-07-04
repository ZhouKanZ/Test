package com.zkqueen.test

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

/**
 * @author: yifeng
 * @date: 2021/6/15
 * @version:
 * @desc:
 */
class BottomSheetTestActivity : AppCompatActivity() {

    private val customClickableArea: Rect = Rect(0, 0, 100, 100)
    private var touchDownTimestamp: Long = 0
    private val CLICK_TIME_DURATION = 100 // 100ms
    var fragment: BSDFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        show.setOnClickListener {
            fragment = fragment ?: BSDFragment()
            fragment!!.show(supportFragmentManager, "bsd")
        }
        test.setOnClickListener {
            Toast.makeText(BottomSheetTestActivity@ this, "我是测试我是测试", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (fragment?.isVisible == true) {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (customClickableArea.contains(event.x.toInt(), event.y.toInt())) {
                        touchDownTimestamp = System.currentTimeMillis()
                        return true
                    }
                }

                MotionEvent.ACTION_UP -> {
                    if (customClickableArea.contains(event.x.toInt(), event.y.toInt())) {
                        val nowTime = System.currentTimeMillis()
                        if (nowTime - touchDownTimestamp <= CLICK_TIME_DURATION) {
                            Toast.makeText(BottomSheetTestActivity@this, "我被点了", Toast.LENGTH_SHORT).show()
                            return true
                        }

                    }
                }
            }
        }
        return super.onTouchEvent(event)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("log-zk", "onBackPressed: ")
    }

}