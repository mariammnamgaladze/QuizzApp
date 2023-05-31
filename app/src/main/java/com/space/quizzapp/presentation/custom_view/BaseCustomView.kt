package com.space.quizzapp.presentation.custom_view
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.graphics.Path
import android.view.View

abstract class BaseCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    protected val rectanglePath by lazy { Path() }
    protected val shadePath by lazy { Path() }

    abstract fun startDrawing(canvas: Canvas, width: Float, height: Float)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        startDrawing(canvas,width,height)
    }
}