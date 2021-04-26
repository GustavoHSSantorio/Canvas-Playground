package com.example.canvasplayground.view.geometric

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import com.example.canvasplayground.view.animations.ShadowClickAnimationView

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : ShadowClickAnimationView(context, attrs, defStyleAttr){

    init {
        paint.color = Color.YELLOW
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path, paint)
        super.onDraw(canvas)
    }

    override fun calculatePath() {
        super.calculatePath()
        val cx = (contentRect.right + contentRect.left)/2 /* largura total considerando o padding dividido por dois*/
        val cy = (contentRect.bottom + contentRect.top)/2 /* altura total considerando o padding dividido por dois*/

        val smallSide =
            if(contentRect.bottom > contentRect.right)
                contentRect.right
            else
                contentRect.bottom

        val radius = (smallSide / 2) /*maior dimensão dividido por dois*/

        path.reset()
        path.addCircle(cx,
            cy,
            radius,
            Path.Direction.CW)
    }

}