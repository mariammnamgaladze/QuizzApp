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

    private val answersStateViews: MutableList<AnswersStateView> = mutableListOf()
    private var onStateViewClickListener: ((Boolean) -> Unit)? = null

    init {
        orientation = VERTICAL
    }

    fun setOnStateViewClickListener(listener: (Boolean) -> Unit) {
        onStateViewClickListener = listener
    }

    fun setAnswersList(quizQuestions: QuizItemUIModel.QuizQuestionUIModel) {
        clearAnswers()
        val correctAnswer = quizQuestions.correctAnswer
        // Variable to track if any answer has been clicked
        var isAnswerClicked = false

        quizQuestions.answers.forEach { answer ->
            AnswersStateView(context).apply {
                // Set the initial state
                setState(AnswersStateView.State.Default)
                setText(answer)
                setOnClickListener {
                    if (!isAnswerClicked) {
                        // Allow click only if no answer has been clicked yet
                        setState(if (answer == correctAnswer) AnswersStateView.State.Correct(true) else AnswersStateView.State.Wrong)
                        if (answer != correctAnswer) {
                            // Find the state view representing the correct answer
                            val correctStateView = answersStateViews.find { it.getText() == correctAnswer }
                            correctStateView?.setState(AnswersStateView.State.Correct(false))
                        }
                        onStateViewClickListener?.invoke(answer == correctAnswer)
                        isAnswerClicked = true
                    }
                }
            }.also {
                answersStateViews.add(it)
                addView(it)
            }
        }
    }

    private fun clearAnswers() {
        removeAllViews()
        answersStateViews.clear()
    }
}
