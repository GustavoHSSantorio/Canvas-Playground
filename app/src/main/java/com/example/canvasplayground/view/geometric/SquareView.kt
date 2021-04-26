package com.example.canvasplayground.view.geometric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import com.example.canvasplayground.view.animations.ShadowClickAnimationView

class SquareView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : ShadowClickAnimationView(context, attrs, defStyleAttr){

    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }

    override fun calculatePath(){
        super.calculatePath()
        val smallSide =
            if(contentRect.right > contentRect.bottom)
                contentRect.bottom
            else
                contentRect.right


        dataRect.apply {
            set(contentRect.left,
                contentRect.top,
                smallSide,
                smallSide)
        }

        path.reset()
        path.addRect(dataRect , Path.Direction.CW)
    }
}