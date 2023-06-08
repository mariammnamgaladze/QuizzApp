package com.space.quizzapp.presentation.authentication.fragment

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.lifecycleScope
import com.space.quizzapp.common.extensions.showToast
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.authentication.viewmodel.AuthenticationViewModel
import kotlin.reflect.KClass
import com.space.quizzapp.common.resource.Result
import com.space.quizzapp.databinding.FragmentAuthenticationBinding

class AuthenticationFragment : BaseFragment<AuthenticationViewModel>() {
    private val binding by viewBinding(FragmentAuthenticationBinding::bind)

    override val viewModelClass: KClass<AuthenticationViewModel>
        get() = AuthenticationViewModel::class
    override val layout: Int
        get() = R.layout.fragment_authentication

    override fun onBind(viewModel: AuthenticationViewModel) {
        setListeners(viewModel)
    }

    private fun setListeners(viewModel: AuthenticationViewModel) {
        binding.startButton.setOnClickListener {
            observeUsernameAvailability(viewModel)
        }
    }

    private fun observeUsernameAvailability(viewModel: AuthenticationViewModel) {
        val username = binding.usernameEditText.text.toString()
        viewModel.checkUsernameAvailability(username)
        lifecycleScope {
            viewModel.usernameAvailability.collect { result ->
                if (result is Result.Success) {
                    viewModel.updateUserActiveStatus(username, true)
                    navigateToHome()
                } else if (result is Result.Error) {
                    requireContext().showToast(getString(R.string.incorrect_input))
                }
            }
        }
    }

    private fun navigateToHome() {
        navigateTo(R.id.action_startFragment_to_homeFragment)
    }
}