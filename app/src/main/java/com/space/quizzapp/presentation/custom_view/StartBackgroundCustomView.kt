package com.space.quizzapp.presentation.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.space.quizzapp.R

class StartBackgroundCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseCustomView(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val blueColor = ContextCompat.getColor(context, R.color.blue_secondary_default)
    private val blueFadeColor = ContextCompat.getColor(context, R.color.blue_secondary_light)

    /**
     * Draws a vector on the top.
     */
    private fun drawVector(canvas: Canvas, width: Float, height: Float) {
        rectanglePath.apply {
            paint.color = blueColor
            moveTo(0f, 0f)
            lineTo(width, 0f)
            arcTo(
                RectF(0f, 0f, width, height),
                0f, 90f
            )
            lineTo(0f, height)
            close()
        }
        canvas.drawPath(rectanglePath, paint)
    }

    /**
     * Draws a shade for vector on the top.
     */
    private fun drawShade(canvas: Canvas, width: Float, height: Float) {
        shadePath.apply {
            paint.color = blueFadeColor
            moveTo(width, 0f)
            arcTo(
                RectF(0f, 0f, width, height),
                0f, 90f
            )
            lineTo(0f, height)
            arcTo(
                RectF(0f, 0f, width, height),
                180f, 90f
            )
            close()
        }
        canvas.drawPath(shadePath, paint)
    }

    override fun startDrawing(canvas: Canvas, width: Float, height: Float) {
        drawVector(canvas, width, height)
        drawShade(canvas, width, height)
    }
}
