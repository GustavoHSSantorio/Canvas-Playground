package com.example.canvasplayground.view.geometric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import com.example.canvasplayground.view.animations.ShadowClickAnimationView

class CilinderView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : ShadowClickAnimationView(context, attrs, defStyleAttr){

    init {
        paint.color = Color.GRAY
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }

    override fun calculatePath() {
        super.calculatePath()
        path.reset()

        val cx = (contentRect.right + contentRect.left)/2

        val leftPoint = cx - cx/2
        val rightPoint = cx + cx/2

        val circleHeight = contentRect.bottom/8
        val circleHalfHeight = circleHeight / 2

        val lineBottomPoint = contentRect.bottom - circleHalfHeight

        path.addOval(RectF(leftPoint, contentRect.top, rightPoint, circleHeight), Path.Direction.CW)
        path.addOval(RectF(leftPoint, contentRect.bottom - circleHeight, rightPoint, contentRect.bottom), Path.Direction.CW)

        path.moveTo(leftPoint, lineBottomPoint)
        path.lineTo(leftPoint, circleHalfHeight)
        path.moveTo(rightPoint, lineBottomPoint)
        path.lineTo(rightPoint, circleHalfHeight)
    }
}