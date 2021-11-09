package com.example.canvasplayground.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.canvasplayground.R
import com.example.canvasplayground.databinding.ActivityLinechartBinding
import com.example.canvasplayground.view.chart.LineChartAdapter

class LineChartActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLinechartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinechartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.linechart.apply {
                lineColor = ContextCompat.getColor(this@LineChartActivity, R.color.black)
                (this.adapter as LineChartAdapter).setData(floatArrayOf( 55f, 80f, 72f, 99f, 66f, 47f, 42f, 56f, 64f, 66f, 80f, 97f,68f, -22f, 31f, 57f, 35f, -79f, 86f, 47f, 34f, 10f, 43f, 12f, 25f, -71f, 47f, 73f, -49f, 36f))
            }
            binding.linechartfill.apply {
                lineColor = ContextCompat.getColor(this@LineChartActivity, R.color.teal_200)
                fillColor = ContextCompat.getColor(this@LineChartActivity, R.color.teal_200)
                (this.adapter as LineChartAdapter).setData(floatArrayOf(68f, -22f, 31f, 57f, 35f, -79f, 86f, 47f, 34f, 55f, 80f, 72f, 99f, 66f, 47f, 42f, 56f, 64f, 66f, 80f, 97f, 10f, 43f, 12f, 25f, -71f, 47f, 73f, -49f, 36f))
            }
            binding.linechartgradient.apply {
                lineColor = ContextCompat.getColor(this@LineChartActivity, R.color.purple_700)
                fillColor = ContextCompat.getColor(this@LineChartActivity, R.color.purple_700)
                (this.adapter as LineChartAdapter).setData(floatArrayOf(47f, 42f, 56f, 64f, 66f, 80f, 97f, 10f, 43f, 12f, 25f, -71f, 47f, 73f, -49f,68f, -22f, 31f, 57f, 35f, -79f, 86f, 47f, 34f, 55f, 80f, 72f, 99f, 66f, 36f))
            }
        }

        binding.linechart.apply {
            adapter = LineChartAdapter()
            lineWidth = resources.getDimension(R.dimen.line_width)
            (this.adapter as LineChartAdapter).setData(floatArrayOf(68f, -22f, 31f, 57f, 35f, -79f, 86f, 47f, 34f, 55f, 80f, 72f, 99f, 66f, 47f, 42f, 56f, 64f, 66f, 80f, 97f, 10f, 43f, 12f, 25f, -71f, 47f, 73f, -49f, 36f))
        }

        binding.linechartfill.apply {
            adapter = LineChartAdapter()
            lineWidth = resources.getDimension(R.dimen.line_width)
            (this.adapter as LineChartAdapter).setData(floatArrayOf(47f, 42f, 56f, 64f, 66f, 80f, 97f, 10f, 43f, 12f, 25f, -71f, 47f, 73f, -49f,68f, -22f, 31f, 57f, 35f, -79f, 86f, 47f, 34f, 55f, 80f, 72f, 99f, 66f, 36f))
        }

        binding.linechartgradient.apply {
            adapter = LineChartAdapter()
            lineWidth = resources.getDimension(R.dimen.line_width)
            (this.adapter as LineChartAdapter).setData(floatArrayOf( 55f, 80f, 72f, 99f, 66f, 47f, 42f, 56f, 64f, 66f, 80f, 97f,68f, -22f, 31f, 57f, 35f, -79f, 86f, 47f, 34f, 10f, 43f, 12f, 25f, -71f, 47f, 73f, -49f, 36f))
        }
    }
}