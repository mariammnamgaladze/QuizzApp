package com.space.quizzapp.presentation.custom_view
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.space.quizzapp.R

/**
 * A custom view that draws a blue vector on the top.
 */
class QuizBackgroundCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) :
    BaseCustomView(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.blue_secondary_lighter)
    }

    override fun startDrawing(canvas: Canvas, width: Float, height: Float) {
        rectanglePath.apply {
            moveTo(0f, 0f)
            lineTo(width, 0f)
            arcTo(
                RectF(width / 2, 0f,width, height),
                0f, 90f
            ) // Add an arc to the path as a new contour.
            arcTo(
                RectF(0f, 0f, width / 2, height),
                90f, 90f
            ) // Add an arc to the path as a new contour.
            close()
        }
        canvas.drawPath(rectanglePath, paint)
    }
}