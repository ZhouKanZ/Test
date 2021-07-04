package com.zkqueen.test

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

/**
 *  多行支持 尾部追加的TextView
 */
class TailTextView : View {

    @DrawableRes
    var startIcon: Int = R.mipmap.bbs_ic_hot
    var contentText: String = "GgPpJjAaBbCcFf"

    @ColorInt
    var contentColor: Int = Color.BLACK
    var tailText: String = "评论600w"
    var endSuffix: String = "..."

    @ColorInt
    var tailTextColor: Int = Color.GRAY
    var textSize: Int = 80
    var isTailEnd = true
    var maxLines = 2

    var contentPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var tailPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var linePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var startBitmap :Bitmap

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val attr = context?.obtainStyledAttributes(attrs, R.styleable.TailTextView)
        initAttrs(context, attr)
        attr?.recycle()
    }

    init {
        contentPaint.color = contentColor
        contentPaint.textSize = textSize.toFloat()

        tailPaint.color = tailTextColor
        tailPaint.textSize = textSize.toFloat()
        startBitmap = BitmapFactory.decodeResource(resources,startIcon)

        linePaint.color = Color.RED
        linePaint.strokeWidth = 2f
        linePaint.style = Paint.Style.FILL
    }

    /**
     *  初始化属性
     */
    private fun initAttrs(context: Context?, attr: TypedArray?) {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val fontSpacing = contentPaint.fontSpacing
        val fontMetrics = contentPaint.fontMetrics
        val rect = Rect()
        contentPaint.getTextBounds("ABF",0,2,rect)

        val aimRect = Rect()
        aimRect.top = (((fontSpacing + fontMetrics.descent + fontSpacing+fontMetrics.ascent)/2) - fontSpacing/2).toInt()
        aimRect.bottom = (aimRect.top + fontSpacing).toInt()
        aimRect.left = 0
        aimRect.right = (aimRect.left + fontSpacing).toInt()

//        canvas?.apply {
//            save()
//            drawLine(0f,fontSpacing + fontMetrics.top, width.toFloat(),fontSpacing + fontMetrics.top,linePaint)
//            drawLine(0f,fontSpacing + fontMetrics.ascent, width.toFloat(),fontSpacing + fontMetrics.ascent,linePaint)
//            drawLine(0f,fontSpacing + fontMetrics.descent, width.toFloat(),fontSpacing + fontMetrics.descent,linePaint)
////            drawLine(0f,fontSpacing + fontMetrics.bottom, width.toFloat(),fontSpacing + fontMetrics.bottom,linePaint)
//            drawLine(0f,fontSpacing , width.toFloat(),fontSpacing,linePaint)
//            restore()
//        }

        canvas?.drawBitmap(startBitmap,null,aimRect,null)

        val tailWidth = contentPaint.measureText(tailText) // 尾部的宽度
        val endSuffixWidth = contentPaint.measureText(endSuffix) // 省略号的宽度
        val freeWidth = width - tailWidth
        val fa = FloatArray(3)

        var rowNum = 0
        var freeText = contentText // 剩余文字

        var currentTextCount = contentPaint.breakText(freeText, true, width.toFloat(), fa)

        while (currentTextCount > 0) {

            val lastRow = isLastRow(rowNum, fa[0], freeWidth, freeText, currentTextCount)
            Log.d(TAG, "onDraw: {currentTextCount :$currentTextCount ,rowNum:$rowNum}")
            // 不是最后一行
            if (!lastRow.first){
                canvas?.drawText(freeText.substring(rowNum, currentTextCount), 0f, (rowNum + 1) * fontSpacing, contentPaint)
            }else{
                // 需要拼接"..."
                if (lastRow.second){
                    val num = contentPaint.breakText(freeText, true, width.toFloat() - tailWidth - endSuffixWidth, fa)
                    canvas?.drawText(freeText.substring(rowNum, num)+"...", 0f, (rowNum + 1) * fontSpacing, contentPaint)
                }else{
                    canvas?.drawText(freeText.substring(rowNum, currentTextCount), 0f, (rowNum + 1) * fontSpacing, contentPaint)
                }
                canvas?.drawText(tailText, width-tailWidth, (rowNum + 1) * fontSpacing, tailPaint)
                break
            }

            rowNum++
            // 重新截取text
            freeText = freeText.substring(currentTextCount, freeText.length)
            currentTextCount = contentPaint.breakText(freeText, true, width.toFloat(), fa)
        }


    }

    /**
     *  是否是最后一行
     *  @return pair 第一个参数表示是否是最后一行，第二个参数表示，是否需要拼接末尾省略号
     */
    private fun isLastRow(currentRowNum: Int, currentRowMeasureWidth: Float, minWidth: Float,
                          freeText: String, currentTextCount: Int): Pair<Boolean,Boolean> {
        val isLastLine = currentRowNum+1 >= maxLines
                || currentRowMeasureWidth <= minWidth
                || currentTextCount >= freeText.length
        var needAppendEllipsis =  currentRowMeasureWidth > minWidth
        return Pair(isLastLine,needAppendEllipsis)
    }

    companion object {
        private const val TAG = "TailTextView"
    }

}