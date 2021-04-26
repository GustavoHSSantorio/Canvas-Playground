package com.example.canvasplayground

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

        binding.sonar.setOnClickListener {
        }
    }
}