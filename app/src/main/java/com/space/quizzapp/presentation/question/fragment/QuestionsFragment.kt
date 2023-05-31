package com.space.quizzapp.presentation.question.fragment
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentQuestionsBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.detail.viewmodel.DetailsFragmentViewModel
import com.space.quizzapp.presentation.question.viewmodel.QuestionsFragmentViewModel
import kotlin.reflect.KClass

class QuestionsFragment : BaseFragment<QuestionsFragmentViewModel>() {
    override val viewModelClass: KClass<QuestionsFragmentViewModel>
        get() = QuestionsFragmentViewModel::class
    private val binding by viewBinding(FragmentQuestionsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_questions

    override fun onBind(viewModel: QuestionsFragmentViewModel) {
        setListeners()
    }

    private fun setListeners() {
        binding.exitImageView.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        navigateTo(R.id.action_questionsFragment_to_homeFragment)
    }
}