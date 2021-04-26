package com.example.canvasplayground.view.geometric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import com.example.canvasplayground.view.animations.ShadowClickAnimationView

class TriangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : ShadowClickAnimationView(context, attrs, defStyleAttr){

    init {
        paint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }

    override fun calculatePath() {
        super.calculatePath()
        path.reset()

        val cx = (contentRect.right + contentRect.left)/2
        val points = listOf(
            Pair(contentRect.right, contentRect.bottom),
            Pair(contentRect.left, contentRect.bottom),
            Pair(cx, contentRect.top),
            Pair(contentRect.right, contentRect.bottom),
            Pair(contentRect.left, contentRect.bottom))

        points.forEachIndexed { index, pair ->
            if(index == 0)
                path.moveTo(pair.first, pair.second)
            else
                path.lineTo(pair.first, pair.second)
        }
    }
}