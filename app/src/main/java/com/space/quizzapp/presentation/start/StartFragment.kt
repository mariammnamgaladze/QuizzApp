package com.space.quizzapp.presentation.start

import androidx.navigation.fragment.findNavController
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentStartBinding
import com.space.quizzapp.presentation.base.BaseFragment

class StartFragment : BaseFragment() {
    private val binding by viewBinding(FragmentStartBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_start

    override fun onBind() {
        setListeners()
    }

    private fun setListeners() {
        binding.startButton.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(
            R.id.action_startFragment_to_homeFragment
        )
    }
}