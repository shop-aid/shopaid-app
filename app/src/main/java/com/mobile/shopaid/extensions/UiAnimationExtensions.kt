package com.mobile.shopaid.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 10:05
 */

fun View.animateView(vararg args: ObjectAnimator, block: () -> Unit = {}) {
    val animatorSet = AnimatorSet()
    animatorSet.interpolator = AccelerateDecelerateInterpolator()
    animatorSet.playSequentially(args.asList())

    animatorSet.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            block()
            animatorSet.removeAllListeners()
        }
    })

    animatorSet.start()
}

fun View.getFadeInAnimator(interDelay: Long): ObjectAnimator {
    val fadeInAnimator = ObjectAnimator
            .ofFloat(this, "alpha", 0f, 1f)
    fadeInAnimator.duration = interDelay
    return fadeInAnimator
}

fun View.getFadeOutAnimator(interDelay: Long): ObjectAnimator {
    val fadeOutAnimator = ObjectAnimator
            .ofFloat(this, "alpha", 1f, 0f)
    fadeOutAnimator.duration = interDelay
    return fadeOutAnimator
}