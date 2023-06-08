package com.space.quizzapp.presentation.detail.fragment

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentDetailsBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.detail.viewmodel.DetailsViewModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_details

    override fun onBind(viewModel: DetailsViewModel) {
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
