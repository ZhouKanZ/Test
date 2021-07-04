package com.zkqueen.test.scrollconflict

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zkqueen.test.R
import kotlinx.android.synthetic.main.activity_conflict.*
import java.util.*

/**
 * @author: yifeng
 * @date: 2021/6/23
 * @version:
 * @desc:
 */
class VerticalScrollActivity : AppCompatActivity() {

    /* 滑动冲突 原型 */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conflict)
        val data = ArrayList<String>()
        for (i in 0..100) {
            data.add("我是第${i}个参数")
        }
        rv.adapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_item,data) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tv_name,item)
            }
        }
    }
}