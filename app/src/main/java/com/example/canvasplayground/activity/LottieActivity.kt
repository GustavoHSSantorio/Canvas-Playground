package com.example.canvasplayground.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.canvasplayground.databinding.ActivityLottieBinding

class LottieActivity : AppCompatActivity() {

    lateinit var binding : ActivityLottieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}