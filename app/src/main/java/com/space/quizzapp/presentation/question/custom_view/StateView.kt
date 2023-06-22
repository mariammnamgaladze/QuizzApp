package com.space.quizzapp.presentation.question.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.space.quizzapp.R
import com.space.quizzapp.databinding.AnswersLayoutItemBinding


class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        AnswersLayoutItemBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    private var currentState: State = State.Default

    fun setText(text: String) {
        binding.questionTextView.text = text
    }

    fun setState(state: State) {
        currentState = state
        val color = ContextCompat.getColor(context, state.color)
        binding.cardView.setCardBackgroundColor(color)
        setAnswerState(state)
        Log.d("StateView", "setState: $state")
        invalidate() // Redraw the view after state change
    }

    private fun setAnswerState(state: State) {
        val textColor =
            if (state is State.Correct || state is State.Wrong) Color.WHITE else Color.BLACK
        val iconVisibility =
            if (state is State.Correct && state.isAnswerCorrect) VISIBLE else INVISIBLE
        with(binding) {
            questionTextView.setTextColor(textColor)
            pointsTextView.setTextColor(textColor)
            pointsTextView.visibility = iconVisibility
        }
    }

    fun getText(): String {
        return binding.questionTextView.text.toString()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    sealed class State(@ColorRes val color: Int) {
        object Default : State(R.color.neutral_03_light_grey)
        object Wrong : State(R.color.wrong)
        data class Correct(val isAnswerCorrect: Boolean) : State(R.color.success)
    }
}

