package com.space.quizzapp.presentation.custom_view
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.graphics.Path
import android.view.View

abstract class BaseCustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    // The paint object responsible for drawing on the canvas
    protected open val paint = Paint().apply {
        isAntiAlias = true
    }

    // The path object used for defining drawing paths on the canvas
    protected val path = Path()

    // Returns the width of the custom view
    protected val width: Float
        get() = getWidth().toFloat()

    // Returns the height of the custom view
    protected val height: Float
        get() = getHeight().toFloat()

    // Draws the background of the custom view on the canvas
    abstract fun drawBackground(canvas: Canvas)

    // Binds the data to the custom view and performs drawing operations on the canvas
    abstract fun onBind(canvas: Canvas)

    // Called when the custom view needs to be rendered on the canvas
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        onBind(canvas)
    }
}
