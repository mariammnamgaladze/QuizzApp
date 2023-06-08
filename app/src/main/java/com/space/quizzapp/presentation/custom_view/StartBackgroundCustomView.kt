package com.space.quizzapp.presentation.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.space.quizzapp.R

class StartBackgroundCustomView(
    context: Context,
    attrs: AttributeSet
) : BaseCustomView(context, attrs) {

    // Paint object for drawing
    override val paint = Paint().apply {
        style = Paint.Style.FILL
    }
    // X-coordinate of the center
    private val centerX: Float
        get() = width / 2f
    // Radius of the circles
    private val radius: Float
        get() = width / 2f

    // Draws the background of the custom view
    override fun drawBackground(canvas: Canvas) {
        val verticalOffset = (width / 2) - (height / 3)
        val centerY1 = height / 3 + verticalOffset
        val centerY2 = height * 2 / 3 - verticalOffset

        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_light)
            addCircle(centerX, centerY1, radius, Path.Direction.CW)
            addCircle(centerX, centerY2, radius, Path.Direction.CW)

            moveTo(centerX, 0f)
            lineTo(width.toFloat(), 0f)
            lineTo(width.toFloat(), centerY2)
            lineTo(centerX, height.toFloat())
            lineTo(0f, height.toFloat())
            lineTo(0f, centerY1)
            lineTo(centerX, 0f)
            close()
            canvas.drawPath(this, paint)
        }
    }
    // Draws the corner shape
    private fun drawCornerShape(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_default)
            moveTo(0f, 0f)
            lineTo(centerX, 0f)
            lineTo(0f, height / 2f)
            close()
            canvas.drawPath(this, paint)
        }
    }
    // Binds the data and performs drawing operations on the canvas
    override fun onBind(canvas: Canvas) {
        drawCornerShape(canvas)
        drawBackground(canvas)
    }
}
