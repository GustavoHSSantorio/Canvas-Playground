package com.example.canvasplayground.view.animations

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.example.canvasplayground.view.CanvasView

class RotationAnimation  @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0) : CanvasView(context, attrs, defStyleAttr) {

    private var viewRealWidth: Float = 0f
    private var viewRealHeight: Float = 0f
    private var viewRealSizePercentageByFour: Float = 0f
    private var viewCX: Float = 0f
    private var viewCY: Float = 0f
    private var loadingDegree: Float = 0f

    private val anim = ValueAnimator.ofFloat(0f, 360f).apply {
        repeatMode = ValueAnimator.RESTART
        repeatCount = ValueAnimator.INFINITE
    }

    init {
        anim.duration = 1000
        configureAnimation()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.rotate(loadingDegree, viewCX, viewCY)
        canvas?.drawLine(
                viewCX - 50f,
                (viewCY), viewCX + 50f, (viewCY), paint
        )
        canvas?.restore()
    }

    private fun configureAnimation() {
        anim.addUpdateListener {
            loadingDegree = it.animatedValue as Float
            calculateLoadingFields()
        }
        anim.start()
    }

    private fun calculateLoadingFields() {
        viewCX = (width / 2).toFloat()
        viewCY = (height / 2).toFloat()
        invalidate()
    }

    override fun calculatePath() {
        calculateLoadingFields()
    }
}