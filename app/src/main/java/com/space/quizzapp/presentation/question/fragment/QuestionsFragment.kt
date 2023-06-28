package com.space.quizzapp.presentation.question.fragment

import androidx.navigation.fragment.navArgs
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentQuestionsBinding
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.dialog.fragment.QuizzDialogFragment
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
                val dialogFragment =
                    QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.ONE_BUTTON)
                        .setImageView(requireContext().getDrawable(R.drawable.ic_congrats)!!)
                        .setCommonTextViewText(requireContext().getString(R.string.congrats))
                        .setCollectedPointsText(
                            requireContext().getString(
                                R.string.collected_points,
                                point
                            )
                        )
                        .setCloseText(requireContext().getString(R.string.close))
                        .setButtonAction {
                            viewModel.navigateToHome()
                        }
                        .build()
                dialogFragment.show(parentFragmentManager, null)
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
