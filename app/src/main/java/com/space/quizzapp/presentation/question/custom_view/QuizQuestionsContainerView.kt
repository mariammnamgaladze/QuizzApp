package com.space.quizzapp.presentation.question.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.space.quizzapp.presentation.model.QuizItemUIModel

class QuizQuestionsContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val stateViews: MutableList<StateView> = mutableListOf()
    private var onStateViewClickListener: ((StateView) -> Unit)? = null

    init {
        orientation = VERTICAL
    }

    fun setOnStateViewClickListener(listener: (StateView) -> Unit) {
        onStateViewClickListener = listener
    }

    fun setAnswersList(quizQuestions: QuizItemUIModel.QuizQuestionUIModel) {
        clearAnswers()
        val correctAnswer = quizQuestions.correctAnswer
        // Variable to track if any answer has been clicked
        var isAnswerClicked = false

        quizQuestions.answers.forEach { answer ->
            StateView(context).apply {
                // Set the initial state
                setState(StateView.State.Default)
                setText(answer)
                setOnClickListener {
                    if (!isAnswerClicked) {
                        // Allow click only if no answer has been clicked yet
                        setState(if (answer == correctAnswer) StateView.State.Correct(true) else StateView.State.Wrong)
                        if (answer != correctAnswer) {
                            // Find the state view representing the correct answer
                            val correctStateView = stateViews.find { it.getText() == correctAnswer }
                            correctStateView?.setState(StateView.State.Correct(false))
                        }
                        onStateViewClickListener?.invoke(this)
                        isAnswerClicked = true
                    }
                }
            }.also {
                stateViews.add(it)
                addView(it)
            }
        }
    }

    private fun clearAnswers() {
        removeAllViews()
        stateViews.clear()
    }
}
