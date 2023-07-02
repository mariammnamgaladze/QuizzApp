package com.space.quizzapp.presentation.question.fragment

import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentQuestionsBinding
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.dialog.fragment.QuizzDialogFragment
import com.space.quizzapp.presentation.home.fragment.HomeFragmentDirections
import com.space.quizzapp.presentation.question.custom_view.ProgressView
import com.space.quizzapp.presentation.question.viewmodel.QuestionsViewModel
import kotlin.reflect.KClass

class QuestionsFragment : BaseFragment<QuestionsViewModel>() {
    override val viewModelClass: KClass<QuestionsViewModel>
        get() = QuestionsViewModel::class
    private val binding by viewBinding(FragmentQuestionsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_questions
    private val args: QuestionsFragmentArgs by navArgs()


    private lateinit var progressView: ProgressView

    override fun onBind() {
        val quiz = args.item
        binding.subjectTextView.text = quiz.quizTitle
        viewModel.quizModel = quiz
        observer()
        viewModel.getQuiz()
        setListeners()
        initProgressView()
    }

    private fun observer() {

        collectAsync(viewModel.quizItem) {
            it?.let {
                progressView.setCurrentQuestion(
                    it.questionIndex + 1,
                    viewModel.quizModel.questionsCount
                )
                binding.materialButton.isEnabled = false
                binding.questionsTextView.text = it.questionTitle
                binding.quizContainerView.setAnswersList(it)
            }
        }

        collectAsync(viewModel.currentScore) {
            it?.let {
                progressView.setCurrentPoint(
                    requireContext().getString(R.string.current_point),
                    it
                )
            }
        }
        collectAsync(viewModel.buttonText) {
            it?.let {
                binding.materialButton.text = getString(it)
            }
        }

        collectAsync(viewModel.finalScore) { point ->
            point?.let { point ->
                val dialogFragment =
                    QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.ONE_BUTTON)
                        .setImageView(
                            ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.ic_congrats
                            )!!
                        )
                        .setCommonTextViewText(requireContext().getString(R.string.congrats))
                        .setCollectedPointsText(
                            requireContext().getString(
                                R.string.collected_points,
                                point
                            )
                        )
                        .setCloseText(requireContext().getString(R.string.close))
                        .setButtonAction {
                            viewModel.navigateBack()
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
        binding.quizContainerView.itemListener = {
            viewModel.updateCorrectPoints(it)
            binding.materialButton.isEnabled = true
        }
        binding.exitImageView.setOnClickListener {
            setUpDialog()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            setUpDialog()
        }
    }

    private fun initProgressView() {
        progressView = binding.progressView
        with(progressView) {
            setCurrentQuestion(1, viewModel.quizModel.questionsCount)
            setCurrentPoint(requireContext().getString(R.string.current_point), 0)
        }
    }

    private fun setUpDialog() {
        val dialogFragment =
            QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.TWO_BUTTON)
                .setCommonTextViewText(requireContext().getString(R.string.dialog_question))
                .setPositiveButtonBackground(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bkg_yes_button
                    )!!
                )
                .setNegativeButtonBackground(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bkg_no_button
                    )!!
                )
                .setPositiveButtonAction {
                    viewModel.navigateBack()
                }
                .build()
        dialogFragment.show(parentFragmentManager, null)
    }
}
