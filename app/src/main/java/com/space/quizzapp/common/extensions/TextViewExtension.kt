package com.space.quizzapp.common.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

fun TextView.setColoredTextWithPrefix(prefix: String, text: String, @ColorInt color: Int) {
    val spannableString = SpannableString("$prefix$text")
    val colorSpan = ForegroundColorSpan(color)

    spannableString.setSpan(
        colorSpan,
        prefix.length,
        prefix.length + text.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.text = spannableString
}
