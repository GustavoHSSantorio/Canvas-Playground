package com.example.canvasplayground.view.chart

import android.graphics.RectF

class ScaleHelper(adapter: ChartAdapter, contentRect: RectF, lineWidth: Float, fill: Boolean) {
    val width: Float
    val height: Float
    val size: Int
    private val xScale: Float
    private val yScale: Float
    private val xTranslation: Float
    private val yTranslation: Float

    init {
        val leftPadding = contentRect.left
        val topPadding = contentRect.top

        var lineWidthOffset = 0.0f

        if (!fill)
            lineWidthOffset = lineWidth

        this.width = contentRect.width() - lineWidthOffset
        this.height = contentRect.height() - lineWidthOffset

        this.size = adapter.count

        val bounds = adapter.dataBounds

        bounds.inset((if (bounds.width() == 0f) -1 else 0).toFloat(), (if (bounds.height() == 0f) -1 else 0).toFloat())

        val minX = bounds.left
        val maxX = bounds.right
        val minY = bounds.top
        val maxY = bounds.bottom


        this.xScale = width / (maxX - minX)
        this.xTranslation = leftPadding - minX * xScale + lineWidthOffset / 2
        this.yScale = height / (maxY - minY)
        this.yTranslation = minY * yScale + topPadding + lineWidthOffset / 2
    }

    fun getX(rawX: Float): Float =
        rawX * xScale + xTranslation


    fun getY(rawY: Float): Float =
        height - rawY * yScale + yTranslation

    fun getDifferenceBetweenTwoPoints(firstX : Float, secondX : Float) =
            getX(secondX) - getX(firstX)
}