package com.space.quizzapp.presentation.question.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.setColor
import com.space.quizzapp.common.extensions.visible
import com.space.quizzapp.databinding.AnswersLayoutItemBinding


class AnswersStateView @JvmOverloads constructor(
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

    fun setText(text: String) {
        binding.answerTextView.text = text
    }

    fun setCorrectBackground(visiblePoint: Boolean = true) = with(binding) {
        root.setColor(R.color.success)
        pointsTextView.visible(visiblePoint)
        answerTextView.setTextColor(ContextCompat.getColor(context, R.color.neutral_04_white))
    }

    fun setWrongBackground() = with(binding) {
        root.setColor(R.color.wrong)
        pointsTextView.visible(false)
        answerTextView.setTextColor(ContextCompat.getColor(context, R.color.neutral_04_white))

    }
    fun setDefaultBackground() = with(binding) {
        root.setColor(R.color.neutral_03_light_grey)
        pointsTextView.visible(false)
    }
}

