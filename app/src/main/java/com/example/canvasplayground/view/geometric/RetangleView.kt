package com.example.canvasplayground.view.geometric

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import com.example.canvasplayground.view.animations.ShadowClickAnimationView

open class RetangleView @JvmOverloads constructor(
    context: Context,
   attrs: AttributeSet? = null,
   defStyleAttr: Int = 0,
   defStyleRes: Int = 0) : ShadowClickAnimationView(context, attrs, defStyleAttr){

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }

    override fun calculatePath(){
        super.calculatePath()
        val bottomSide =
            if(contentRect.bottom > contentRect.right/2)
                contentRect.right/2
            else
                contentRect.bottom

        dataRect.apply {
            set(contentRect.left,
                contentRect.top,
                contentRect.right,
                bottomSide)
        }

        path.reset()
        path.addRect(dataRect , Path.Direction.CW)
    }
}