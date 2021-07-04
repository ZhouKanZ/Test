package com.zkqueen.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zkqueen.test.scrollconflict.VerticalScrollActivity
import kotlinx.android.synthetic.main.activity_enter.*

/**
 *  @author: yifeng
 *  @date: 2021/6/23
 *  @version:
 *  @desc:
 */
class EnterActivity:AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_enter)

        val pairs = arrayListOf<Pair<String, Class<out Activity>>>()
        pairs.add(Pair("滑动冲突",VerticalScrollActivity::class.java))
        pairs.add(Pair("弹框",BottomSheetTestActivity::class.java))
        pairs.add(Pair("webView内容高度监听",WebViewTestActivity::class.java))

        val mAdapter = object : BaseQuickAdapter<Pair<String, Class<out Activity>>, BaseViewHolder>(
            R.layout.layout_entrance_item,
            pairs
        ) {
            override fun convert(holder: BaseViewHolder, item: Pair<String, Class<out Activity>>) {
                holder.setText(R.id.tv_indicator, item.first)
            }
        }

        rv_entrance.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val itemData = adapter.data[position] as Pair<String, Class<out Activity>>
            startActivity(Intent(EnterActivity@this,itemData.second))
        }


    }


}