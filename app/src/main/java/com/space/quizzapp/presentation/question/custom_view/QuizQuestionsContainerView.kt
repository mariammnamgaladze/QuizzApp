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
        var isAnswerClicked = false // Variable to track if any answer has been clicked

        quizQuestions.answers.forEach { answer ->
            val stateView = StateView(context)
            stateView.setState(StateView.State.DEFAULT) // Set the initial state
            stateView.setText(answer)
            stateView.setOnClickListener {
                if (!isAnswerClicked) { // Allow click only if no answer has been clicked yet
                    stateView.setState(if (answer == correctAnswer) StateView.State.CORRECT else StateView.State.WRONG)
                    if (answer != correctAnswer) {
                        // Find the state view representing the correct answer
                        val correctStateView = stateViews.find { it.getText() == correctAnswer }
                        correctStateView?.setState(StateView.State.UNCLICKEDCORRECT)
                    }
                    onStateViewClickListener?.invoke(stateView)
                    isAnswerClicked = true
                }
            }
            stateViews.add(stateView)
            addView(stateView)
        }
    }
    private fun clearAnswers() {
        removeAllViews()
        stateViews.clear()
    }
}
