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

    private fun setListeners() {
        with(binding) {
            materialButton.setOnClickListener {
                viewModel.getQuiz()
            }
            quizContainerView.itemListener = {
                viewModel.updateCorrectPoints(it)
                materialButton.isEnabled = true
            }
            exitImageView.setOnClickListener {
                setUpPromptDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            setUpPromptDialog()
        }
    }

    private fun initProgressView() {
        progressView = binding.progressView
        with(progressView) {
            setCurrentQuestion(1, viewModel.quizModel.questionsCount)
            setCurrentPoint(requireContext().getString(R.string.current_point), 0)
        }
    }


    private fun observer() {
        collectAsync(viewModel.quizItem) {
            it?.let {
                progressView.setCurrentQuestion(
                    it.questionIndex + 1,
                    viewModel.quizModel.questionsCount
                )
                with(binding) {
                    materialButton.isEnabled = false
                    questionsTextView.text = it.questionTitle
                    quizContainerView.setAnswersList(it)
                }
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
                if (point == 0) {
                    showAlertDialog(point, R.string.no_points_collected, R.string.sad_emoji)
                } else {
                    showAlertDialog(point, R.string.collected_points, R.string.congrats)
                }
            }
        }
    }

    private fun setUpPromptDialog() {
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
                .setPositiveButtonAction { viewModel.navigateBack() }
                .build()
        dialogFragment.show(parentFragmentManager, null)
    }

    private fun showAlertDialog(point: Int, collectedPointsText: Int, alertText: Int) {
        val dialogFragment =
            QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.ONE_BUTTON)
                .setCommonTextViewText(requireContext().getString(alertText))
                .setCollectedPointsText(
                    requireContext().getString(
                        collectedPointsText,
                        point
                    )
                )
                .setCloseText(requireContext().getString(R.string.close))
                .setButtonAction { viewModel.navigateBack() }
                .build()
        dialogFragment.show(parentFragmentManager, null)
    }
}
