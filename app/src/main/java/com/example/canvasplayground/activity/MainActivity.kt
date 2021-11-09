package com.example.canvasplayground.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.canvasplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.slider.setOnClickListener {
            startActivity(Intent(this, SlideAnimationActivity::class.java))
        }

        binding.geometric.setOnClickListener {
            startActivity(Intent(this, GeometricActivity::class.java))
        }

        binding.loading.setOnClickListener {
            startActivity(Intent(this, RotationAnimationActivity::class.java))
        }

        binding.lottie.setOnClickListener {
            startActivity(Intent(this, LottieActivity::class.java))
        }

        binding.linechart.setOnClickListener {
            startActivity(Intent(this, LineChartActivity::class.java))
        }
    }
}