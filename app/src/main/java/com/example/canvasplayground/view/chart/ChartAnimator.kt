package com.example.canvasplayground.view.chart

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.graphics.Path
import android.graphics.PathMeasure
import androidx.annotation.IntRange

interface AnimatedChart{
    fun setAnimationPath(animationPath: Path)
    fun getPath() : Path
}

interface ChartAnimator {
    fun getAnimation(animatedChart: AnimatedChart): Animator?
}

class ChartAnimatorImp : Animator(),
    ChartAnimator {

    private val animator: ValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f)

    override fun getAnimation(animatedChart: AnimatedChart): Animator? {
        val linePath = animatedChart.getPath()
        val pathMeasure = PathMeasure(linePath, false)
        val endLength = pathMeasure.length

        if (endLength <= 0) {
            return null
        }

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            val animatedPathLength = animatedValue * endLength
            linePath.reset()
            pathMeasure.getSegment(0f, animatedPathLength, linePath, true)
            animatedChart.setAnimationPath(linePath)
        }
        return animator
    }

    override fun getStartDelay(): Long {
        return animator.startDelay
    }

    override fun setStartDelay(@IntRange(from = 0) startDelay: Long) {
        animator.startDelay = startDelay
    }

    override fun setDuration(@IntRange(from = 0) duration: Long): Animator {
        return animator.setDuration(duration)
    }

    override fun getDuration(): Long {
        return animator.duration
    }

    override fun setInterpolator(timeInterpolator: TimeInterpolator?) {
        animator.interpolator = timeInterpolator
    }

    override fun isRunning(): Boolean {
        return animator.isRunning
    }
}
