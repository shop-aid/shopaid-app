package com.mobile.shopaid.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 10:05
 */

fun View.animateView(vararg args: ObjectAnimator, block: () -> Unit = {}) {
    val animatorSet = AnimatorSet()
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

fun Fragment.showError(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun Activity.showError(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}