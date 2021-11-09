package com.example.canvasplayground.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.canvasplayground.databinding.ActivitySlideAnimationBinding
import com.example.canvasplayground.view.animations.SliderAnimationView

class SlideAnimationActivity : AppCompatActivity() {


    lateinit var binding : ActivitySlideAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySlideAnimationBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.lala.addView(SliderAnimationView(this))
    }
}