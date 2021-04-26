package com.example.canvasplayground.view.animations

import android.animation.TimeInterpolator
import android.view.animation.*
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

enum class InterpolationType(val interpolator: TimeInterpolator) {
    LINEAR_OUT_SLOW_IN_INTERPOLATOR(LinearOutSlowInInterpolator()),
    ACCELERATE_DECELERATE_INTERPOLATOR(AccelerateDecelerateInterpolator()),
    ACCELERATE_INTERPOLATOR(AccelerateInterpolator()),
    ANTICIPATE_INTERPOLATOR(AnticipateInterpolator()),
    ANTICIPATE_OVERSHOOT_INTERPOLATOR(AnticipateOvershootInterpolator()),
    BOUNCE_INTERPOLATOR(BounceInterpolator()),
    DECELERATE_INTERPOLATOR(DecelerateInterpolator()),
    FAST_OUT_LINEAR_IN_INTERPOLATOR(FastOutLinearInInterpolator()),
    FAST_OUT_SLOW_IN_INTERPOLATOR(FastOutSlowInInterpolator()),
    OVERSHOOT_INTERPOLATOR(OvershootInterpolator())

}