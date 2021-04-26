package com.example.canvasplayground.view

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

abstract class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : View(context, attrs, defStyleAttr){

    protected val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
        strokeWidth = 50f
    }

    protected val contentRect : RectF = RectF()
    protected val dataRect : RectF = RectF()

    protected val path : Path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateContentRect()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        updateContentRect()
    }

    private fun updateContentRect(){
        contentRect.set(
            paddingStart.toFloat() + paint.strokeWidth,
            paddingTop.toFloat() + paint.strokeWidth,
            (width - paddingEnd).toFloat() - paint.strokeWidth,
            (height - paddingBottom).toFloat() - paint.strokeWidth
        )
        calculateAndDispatch()
    }

    protected fun calculateAndDispatch(){
        calculatePath()
        invalidate()
    }

    protected abstract fun calculatePath()
}