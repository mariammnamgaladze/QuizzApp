package com.space.quizzapp.presentation.authentication.fragment

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.common.extensions.showToast
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentAuthenticationBinding
import com.space.quizzapp.presentation.authentication.viewmodel.AuthenticationViewModel
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import kotlin.reflect.KClass

class AuthenticationFragment : BaseFragment<AuthenticationViewModel>() {
    private val binding by viewBinding(FragmentAuthenticationBinding::bind)

    override val viewModelClass: KClass<AuthenticationViewModel>
        get() = AuthenticationViewModel::class
    override val layout: Int = R.layout.fragment_authentication

    override fun onBind() {
        collectAsync(viewModel.errorMessage) {
            requireContext().showToast(getString(R.string.incorrect_input))
        }
        setListeners()
    }

    private fun setListeners() {
        binding.startButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            viewModel.checkUsernameAvailability(username)
        }
    }
}



