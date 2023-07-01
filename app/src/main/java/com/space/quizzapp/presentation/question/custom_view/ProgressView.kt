package com.space.quizzapp.presentation.question.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.space.quizzapp.databinding.ProgressLayoutBinding

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        ProgressLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setCurrentPoint(currentPoint: Int) {
        binding.currentPointTextView.text = currentPoint.toString()
    }

    fun setCurrentQuestion(currentQuestion: Int, maxQuestion: Int) {
        val questionText = "$currentQuestion/$maxQuestion"
        binding.currentQuestionTextView.text = questionText
        val progress =
            (currentQuestion.toFloat() / maxQuestion.toFloat() * binding.progressBar.max).toInt()
        binding.progressBar.progress = progress
    }
}