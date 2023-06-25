package com.space.quizzapp.presentation.question.fragment

import androidx.navigation.fragment.navArgs
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.common.extensions.showToast
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentQuestionsBinding
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.question.viewmodel.QuestionsViewModel
import kotlin.reflect.KClass

class QuestionsFragment : BaseFragment<QuestionsViewModel>() {
    override val viewModelClass: KClass<QuestionsViewModel>
        get() = QuestionsViewModel::class
    private val binding by viewBinding(FragmentQuestionsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_questions
    private val args: QuestionsFragmentArgs by navArgs()


    override fun onBind() {
        val quiz = args.item
        binding.subjectTextView.text = quiz.quizTitle
        viewModel.quizModel = quiz
        observer()
        viewModel.getQuiz()
        setListeners()
    }

    private fun observer() {

        collectAsync(viewModel.quizItem) {
            it?.let {
                binding.materialButton.isEnabled = false
                binding.questionsTextView.text = it.questionTitle
                binding.quizContainerView.setAnswersList(it)
            }
        }

        collectAsync(viewModel.finalScore) { point ->
            point?.let { point ->
                requireContext().showToast(point.toString())
                //TODO Dialog
            }
        }
    }

    private fun setListeners() {
        binding.materialButton.setOnClickListener {
            viewModel.getQuiz()
        }
        binding.quizContainerView.setOnStateViewClickListener {
            viewModel.updateCorrectPoints(it)
            binding.materialButton.isEnabled = true
        }
        binding.exitImageView.setOnClickListener {
            viewModel.navigateToHome()
        }
    }
}
