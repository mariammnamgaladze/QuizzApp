package com.space.quizzapp.presentation.detail.fragment

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentDetailsBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.detail.viewmodel.DetailsFragmentViewModel
import com.space.quizzapp.presentation.start.viewmodel.AuthenticationFragmentViewModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsFragmentViewModel>() {

    override val viewModelClass: KClass<DetailsFragmentViewModel>
        get() = DetailsFragmentViewModel::class
    private val binding by viewBinding(FragmentDetailsBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_details

    override fun onBind(viewModel: DetailsFragmentViewModel) {
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
