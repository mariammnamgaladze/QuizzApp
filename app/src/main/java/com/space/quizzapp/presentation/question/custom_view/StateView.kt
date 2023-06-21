package com.space.quizzapp.presentation.question.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
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

    enum class State(val color: Int) {
        DEFAULT(R.color.neutral_03_light_grey),
        WRONG(R.color.wrong),
        CORRECT(R.color.success),
        UNCLICKEDCORRECT(R.color.success)
    }

    private var currentState: State = State.DEFAULT


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
            if (state == State.CORRECT || state == State.WRONG || state == State.UNCLICKEDCORRECT) Color.WHITE else Color.BLACK
        val visibility = if (state == State.CORRECT) VISIBLE else INVISIBLE
        with(binding) {
            questionTextView.setTextColor(textColor)
            pointsTextView.setTextColor(textColor)
            pointsTextView.visibility = visibility
        }
    }

    fun getText(): String {
        return binding.questionTextView.text.toString()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}

