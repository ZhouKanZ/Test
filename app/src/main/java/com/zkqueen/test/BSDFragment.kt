package com.zkqueen.test

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_webview_test.*
import kotlinx.android.synthetic.main.fragment_bsd.*

class BSDFragment : BottomSheetDialogFragment() {

    private val customClickableArea : Rect = Rect(0, 100, 300, 300)
    private var touchDownTimestamp:Long = 0
    private val CLICK_TIME_DURATION = 100 // 100ms

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bsd, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onStart() {
        super.onStart()
        val bDialog = dialog as BottomSheetDialog
        // 默认显示的高度 --
        bDialog.behavior.peekHeight = (resources.displayMetrics.heightPixels * 0.6f).toInt() // 目标位置
//        bDialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                if (newState == BottomSheetBehavior.STATE_DRAGGING){
//                    bDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//
//            }
//
//        })

        bDialog.window?.let {
            it.attributes.width = WindowManager.LayoutParams.MATCH_PARENT
            it.attributes.height = WindowManager.LayoutParams.MATCH_PARENT
            it.setDimAmount(0f)
//            it.setBackgroundDrawableResource(R.drawable.dialog_bg)
//            it.setBackgroundDrawable(CustomDrawable(context!!))
        }

        val customTouchDelegate = object :CustomTouchDelegate(customClickableArea){
            override fun performOutSideClickAreaEvent() {
                dismiss()
            }

            override fun performClick() {
                Toast.makeText(context, "我被点了", Toast.LENGTH_SHORT).show()
            }
        }
        ((view?.parent as ViewGroup).parent as ViewGroup).getChildAt(0).setOnTouchListener { v, event ->
            customTouchDelegate.onTouchEvent(event)
        }

        dialog?.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
                activity?.finish()
            }
            false
        }

        a_container.layoutParams.height = (resources.displayMetrics.heightPixels * 0.6).toInt()
        a_container.setOnClickListener {
            Log.d(TAG, "onStart: ")
            a_container.layoutParams.height = resources.displayMetrics.heightPixels
            view!!.requestLayout()

        }

    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSetting() {
        a_container.settings?.let {
            it.javaScriptEnabled = true
            it.useWideViewPort = true
        }

        a_container.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                view?.loadUrl("javascript:AndroidFunction.resize(document.body.scrollHeight)")
            }
        }

        a_container.addJavascriptInterface(AppInterface(), "AndroidFunction")

    }

    inner class AppInterface{

        @JavascriptInterface
        fun resize(height:Float){
            Log.d(TAG, "resize: $height")
            a_container.post {
                if (height > 1500){
                    a_container.layoutParams.height = resources.displayMetrics.heightPixels
                    view!!.requestLayout()
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebViewSetting()
        a_container.loadUrl("http://10.100.101.34/LW/20210630%e6%96%97%e9%b1%bc%e6%98%9f%e6%b5%b7%e7%bb%84%e4%bb%b6%e5%8a%9f%e8%83%bd%e4%bc%98%e5%8c%96/")

//        val recyclerView = view.findViewById<RecyclerView>(R.id.a_container)
//        // 最大高度 动态设置这个值
//        recyclerView.layoutParams.height = (resources.displayMetrics.heightPixels * 0.6f).toInt()
//        val data = ArrayList<String>()
//        for (i in 0..100) {
//            data.add("我是第${i}个参数")
//        }
//        val adapter = object : BaseQuickAdapter<String, BaseViewHolder>(
//            R.layout.layout_item,
//            data
//        ) {
//            override fun convert(holder: BaseViewHolder, item: String) {
//                holder.setText(R.id.tv_name, item)
//            }
//        }
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(activity)
//        // 布局
//        adapter.setOnItemClickListener { adapter, view, position ->
//            recyclerView.layoutParams.height = resources.displayMetrics.heightPixels
//            recyclerView.requestLayout()
//            ViewCompat.offsetTopAndBottom(getView()!!,
//                (resources.displayMetrics.heightPixels * 0.4).toInt()
//            )
//        }


    }

    companion object {
        private const val TAG = "BSDFragment"
    }

}