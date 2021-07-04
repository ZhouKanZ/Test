package com.zkqueen.test

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable

/**
 *  @author: yifeng
 *  @date: 2021/6/21
 *  @version:
 *  @desc: 自定义drawable
 */
class CustomDrawable(val context: Context) : Drawable() {


    lateinit var mPaint: Paint

    override fun draw(canvas: Canvas) {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        val rect1 = Rect(0, 100, 200, 300)
        canvas.drawRect(rect1,mPaint)

        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.icon_video_selector)

        val rect = Rect().apply {
            left = (ScreenUtils.widthPixels(context) - ScreenUtils.dp2px(context, 30f)) / 2
            top = ScreenUtils.dp2px(context, 152f)
            right = left + ScreenUtils.dp2px(context, 30f)
            bottom = top + ScreenUtils.dp2px(context, 24.5f)
        }
        canvas.drawBitmap(icon, null, rect, null)

        mPaint.color = Color.WHITE
        mPaint.textSize = ScreenUtils.sp2px(context,14f).toFloat()
        mPaint.setShadowLayer(10f, 0f, 5f, Color.WHITE)

        mPaint.textAlign = Paint.Align.CENTER
        val centerX = bounds.width() shr 1
        canvas.drawText("现在开拍",
            centerX.toFloat(), ScreenUtils.dp2px(context, 186.5f) -  mPaint.ascent(), mPaint)

    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}