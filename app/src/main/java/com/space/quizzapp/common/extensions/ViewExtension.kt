package com.space.quizzapp.common.extensions

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

fun View.setColor(color: Int) {
    backgroundTintList = ContextCompat.getColorStateList(context, color)
}

fun View.visible(visibility: Boolean) {
    isVisible = visibility
}