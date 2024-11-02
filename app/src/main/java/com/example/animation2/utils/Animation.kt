package com.example.animation2.utils

import android.graphics.drawable.AnimationDrawable
import android.widget.RelativeLayout

object Animation {
    fun backgroundAnimation(relativeLayout: RelativeLayout) {
        val animation : AnimationDrawable = relativeLayout.background as AnimationDrawable
        animation.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }
}