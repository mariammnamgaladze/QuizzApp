package com.space.quizzapp.presentation.custom_view
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import com.space.quizzapp.R

class QuizBackgroundCustomView(
    context: Context,
    attrs: AttributeSet
) : BaseCustomView(context, attrs) {

    override val paint = Paint().apply {
        style = Paint.Style.FILL
    }

    /**[drawBackground] Draws the background of the custom view on the canvas
     */
    override fun drawBackground(canvas: Canvas) {
        val centerX = width / 2
        val radius = width / 4
        val centerY = height - radius

        path.apply {
            reset()
            paint.color = context.getColor(R.color.blue_secondary_default)
            addCircle(centerX, centerY, radius, Path.Direction.CW)

            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, centerY)
            lineTo(centerX + radius, height)
            lineTo(centerX - radius, height)
            lineTo(0f, centerY)
            lineTo(0f, 0f)
            close()
        }
        canvas.drawPath(path, paint)
    }
    // Binds the data and performs drawing operations on the canvas
    override fun onBind(canvas: Canvas) {
        drawBackground(canvas)
    }
}