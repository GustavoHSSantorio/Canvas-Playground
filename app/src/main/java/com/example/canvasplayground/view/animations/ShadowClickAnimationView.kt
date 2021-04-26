package com.example.canvasplayground.view.animations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import com.example.canvasplayground.view.CanvasView

open class ShadowClickAnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : CanvasView(context, attrs, defStyleAttr){

    private var xEvent : Float = -1f
    private var yEvent : Float = -1f

    private val shadowPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.GRAY
    }

    private val shadowPath : Path = Path()

    private var shadowAnimation : Animator? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when(it.action){
                MotionEvent.ACTION_DOWN ->{
                    xEvent = it.x
                    yEvent = it.y
                    calculateAndDispatch()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(shadowPath, shadowPaint)
    }

    override fun calculatePath() {
        shadowAnimation?.cancel()
        shadowAnimation = ValueAnimator.ofInt(255, 0).apply {
            duration = 300
            addUpdateListener {
                val value = it.animatedValue as Int
                val percentage = 1.0f - value.toFloat()/255f
                shadowPaint.alpha = value
                shadowPath.reset()
                shadowPath.addCircle(xEvent, yEvent, 50f * percentage, Path.Direction.CW)
                invalidate()
            }

            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    shadowPath.reset()
                    invalidate()
                }
            })

            start()
        }
    }
}