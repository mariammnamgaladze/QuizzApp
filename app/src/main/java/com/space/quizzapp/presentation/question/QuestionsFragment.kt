package com.space.quizzapp.presentation.question


import androidx.navigation.fragment.findNavController
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentQuestionsBinding
import com.space.quizzapp.presentation.base.BaseFragment

class QuestionsFragment : BaseFragment() {
    private val binding by viewBinding(FragmentQuestionsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_questions

    override fun onBind() {
        setListeners()
    }

    private fun setListeners() {
        binding.exitImageView.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(
            R.id.action_questionsFragment_to_homeFragment
        )
    }
}