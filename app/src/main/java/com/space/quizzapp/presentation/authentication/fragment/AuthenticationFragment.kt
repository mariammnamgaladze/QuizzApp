package com.space.quizzapp.presentation.authentication.fragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.showToast
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.authentication.viewmodel.AuthenticationViewModel
import kotlin.reflect.KClass
import com.space.quizzapp.databinding.FragmentAuthenticationBinding
import kotlinx.coroutines.launch

class AuthenticationFragment : BaseFragment<AuthenticationViewModel>() {

    override val viewModelClass: KClass<AuthenticationViewModel>
        get() = AuthenticationViewModel::class

    private val binding by viewBinding(FragmentAuthenticationBinding::bind)

    override val layout: Int = R.layout.fragment_authentication

    override fun onBind() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorMessage.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                requireContext().showToast(getString(R.string.incorrect_input))
            }
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



