package com.space.quizzapp.presentation.start

import androidx.navigation.fragment.findNavController
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentStartBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.dialog.showSingleButtonDialog

class StartFragment : BaseFragment() {
    private val binding by viewBinding(FragmentStartBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_start

    override fun onBind() {
        setListeners()
    }

    private fun setListeners() {
        binding.startButton.setOnClickListener {
            showSingleButtonDialog(
                requireContext(),
                requireContext().getDrawable(R.drawable.ic_congrats)!!,
                requireContext().getString(R.string.congrats),
                requireContext().getString(R.string.collected_points),
                requireContext().getString(R.string.close),
                buttonAction = {
                    navigateToHome()
                }
            )
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(
            R.id.action_startFragment_to_homeFragment
        )
    }
}