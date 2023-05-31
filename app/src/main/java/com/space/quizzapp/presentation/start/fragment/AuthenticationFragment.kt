package com.space.quizzapp.presentation.start.fragment

import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.start.viewmodel.AuthenticationFragmentViewModel
import kotlin.reflect.KClass
import com.space.quizzapp.common.resource.Result
import com.space.quizzapp.databinding.FragmentAuthenticationBinding
import kotlinx.coroutines.launch


class AuthenticationFragment : BaseFragment<AuthenticationFragmentViewModel>() {
    private val binding by viewBinding(FragmentAuthenticationBinding::bind)

    override val viewModelClass: KClass<AuthenticationFragmentViewModel>
        get() = AuthenticationFragmentViewModel::class
    override val layout: Int
        get() = R.layout.fragment_authentication

    override fun onBind(viewModel: AuthenticationFragmentViewModel) {
        setListeners(viewModel)
    }

    private fun setListeners(viewModel: AuthenticationFragmentViewModel) {
        binding.startButton.setOnClickListener {
            observeUsernameAvailability(viewModel)
        }
    }

    private fun observeUsernameAvailability(viewModel: AuthenticationFragmentViewModel) {
        val username = binding.usernameEditText.text.toString()
        viewModel.checkUsernameAvailability(username)
        // Observe the usernameAvailability state flow
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.usernameAvailability.collect { result ->
                when (result) {
                    is Result.Success -> {
                        viewModel.updateUserActiveStatus(username, true)
                        navigateToHome()
                    }
                    is Result.Loading -> {
                    }
                    is Result.Error -> {
                        Toast.makeText(context, "Invalid username format", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun navigateToHome() {
        navigateTo(R.id.action_startFragment_to_homeFragment)
    }
}