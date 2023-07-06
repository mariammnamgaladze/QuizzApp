package com.space.quizzapp.common.extensions

fun Float.convertToDecimals(numDecimals: Int): String {
    return String.format("%.${numDecimals}f", this)
}