package com.space.quizzapp.presentation.splash.ui

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.splash.viewmode.SplashViewModel
import kotlin.reflect.KClass


class SplashFragment : BaseFragment<SplashViewModel>() {

    override val viewModelClass: KClass<SplashViewModel>
        get() = SplashViewModel::class
    override val layout: Int = R.layout.fragment_splash

    override fun onBind() {
        viewModel.checkIfUserIsLoggedIn()
        collectAsync(viewModel.destinationFlow) { destinationId ->
            findNavController().navigate(destinationId)
        }
    }
}
