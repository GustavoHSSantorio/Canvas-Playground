package com.example.canvasplayground.view.geometric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import com.example.canvasplayground.view.animations.ShadowClickAnimationView

class CubeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : ShadowClickAnimationView(context, attrs, defStyleAttr){

    init {
        paint.color = Color.BLACK
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
            Pair(contentRect.left, contentRect.bottom/3),
            Pair(cx, contentRect.top),
            Pair(contentRect.right, contentRect.bottom/3),
            Pair(contentRect.right, contentRect.bottom/3 * 2),
            Pair(cx, contentRect.bottom),
            Pair(contentRect.left, contentRect.bottom/3 * 2),
            Pair(contentRect.left, contentRect.bottom/3 *1.05f),
            Pair(cx, contentRect.bottom/3 * 2),
            Pair(contentRect.right, contentRect.bottom/3),
            Pair(cx, contentRect.bottom/3 * 2),
            Pair(cx, contentRect.bottom)
            )

        points.forEachIndexed { index, pair ->
            if(index == 0)
                path.moveTo(pair.first, pair.second)
            else
                path.lineTo(pair.first, pair.second)
        }
    }
}