package com.example.canvasplayground.view.animations

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.example.canvasplayground.view.CanvasView
import com.example.canvasplayground.R

class SliderAnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : CanvasView(context, attrs, defStyleAttr) {

    private var animation : Animator? = null
    private val animationInterpolator : TimeInterpolator

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SliderAnimationView,
                        defStyleAttr, defStyleRes)

        a.apply {
            animationInterpolator = InterpolationType
                    .values()[getInt(R.styleable.SliderAnimationView_animation_intepolation, 0)]
                    .interpolator
        }

        a.recycle()
        paint.strokeWidth = 20f
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }

    override fun calculatePath() {
        val cy = (contentRect.bottom + contentRect.top)/2
        val lineWidth = 200f

        animation?.cancel()
        animation = ValueAnimator.ofFloat(contentRect.left, contentRect.right - lineWidth).apply {
            interpolator = animationInterpolator
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE;
            repeatMode = ObjectAnimator.REVERSE

            addUpdateListener {
                val value = it.animatedValue as Float
                path.reset()
                path.moveTo(value, cy)
                path.lineTo(value + lineWidth, cy)
                invalidate()
            }
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animation?.cancel()
    }
}