package com.space.quizzapp.presentation.question.custom_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.view.children
import com.space.quizzapp.presentation.model.remote.QuizItemUIModel

class QuizQuestionsContainerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    lateinit var itemListener: ((Boolean) -> Unit)

    init {
        orientation = VERTICAL
    }

    fun setAnswersList(quizQuestions: QuizItemUIModel.QuizQuestionUIModel) {
        removeAllViews()
        val correctAnswer = quizQuestions.correctAnswer
        quizQuestions.answers.forEach {
            initAnswerViews(
                text = it,
                correctAnswer = correctAnswer,
                correctAnswerPosition = quizQuestions.answers.indexOf(correctAnswer)
            )
        }
    }

    private fun initAnswerViews(
        text: String,
        correctAnswer: String,
        correctAnswerPosition: Int
    ) {
        val answerView = AnswersStateView(context)
        with(answerView) {
            setText(text)
            setDefaultBackground()
            this@QuizQuestionsContainerView.addView(answerView)
            setOnClickListener {
                disableClick()
                if (text == correctAnswer) {
                    setCorrectBackground(true)
                } else {
                    setWrongBackground()
                    getAnswerView(correctAnswerPosition).setCorrectBackground(false)
                }
                itemListener.invoke(text == correctAnswer)
            }
        }
    }

    private fun disableClick(){
        children.forEach {
            it.isClickable = false
        }
    }

    private fun getAnswerView(position: Int) = getChildAt(position) as AnswersStateView
}
