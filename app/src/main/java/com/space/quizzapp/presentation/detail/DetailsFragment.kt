package com.space.quizzapp.presentation.detail

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentDetailsBinding
import com.space.quizzapp.presentation.base.BaseFragment

class DetailsFragment : BaseFragment() {
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_details

    override fun onBind() {
        setListeners()
    }

    private fun setListeners() {
        binding.backImageButton.setOnClickListener {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        navigateTo(
            R.id.action_detailsFragment_to_homeFragment
        )
    }
}
