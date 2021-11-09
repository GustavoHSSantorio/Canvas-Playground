package com.example.canvasplayground.view.chart

open class LineChartAdapter(private var yData: FloatArray = floatArrayOf()) : ChartAdapter() {

    override val count: Int
        get() = yData.size

    open fun setData(yData: FloatArray) {
        this.yData = yData
        notifyDataSetChanged()
    }

    fun setDataWithoutNotify(yData: FloatArray) {
        this.yData = yData
    }

    fun hideGraph() {
        var minValue = Float.MIN_VALUE

        for (aYData in yData) {
            if (aYData < minValue) {
                minValue = aYData
            }
        }
        var i = 0
        while (i < yData.size) {
            yData[i] = minValue
            i++
        }
        notifyDataSetChanged()
    }

    override fun hasBaseLine(): Boolean =
        containsNegativeValue()

    private fun containsNegativeValue(): Boolean {
        for (value in yData) {
            if (value < 0)
                return true
        }
        return false
    }

    override fun getItem(index: Int): Any =
        yData[index]

    override fun getY(index: Int): Float =
        yData[index]

    fun clearData(){
       setData(floatArrayOf())
    }
}