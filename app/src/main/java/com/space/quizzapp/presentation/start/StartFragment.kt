package com.space.quizzapp.presentation.start

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentStartBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.dialog.MyDialogFragment

class StartFragment : BaseFragment() {
    private val binding by viewBinding(FragmentStartBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_start

    override fun onBind() {
        setListeners()
    }

    private fun setListeners() {
        binding.startButton.setOnClickListener {
           /* showSingleButtonDialog(
                requireContext(),
                requireContext().getDrawable(R.drawable.ic_congrats)!!,
                requireContext().getString(R.string.congrats),
                requireContext().getString(R.string.collected_points),
                requireContext().getString(R.string.close),
                buttonAction = {
                    navigateToHome()
                }
            )*/
            MyDialogFragment.oneButtonState(
                requireContext().getDrawable(R.drawable.ic_congrats)!!,
                requireContext().getString(R.string.congrats),
                requireContext().getString(R.string.collected_points),
                requireContext().getString(R.string.close),
                buttonAction = {
                    navigateTo(R.id.action_startFragment_to_homeFragment)
                }
            ).show(parentFragmentManager, "SingleButtonDialog")

        }
    }

    private fun navigateToHome() {
        navigateTo(R.id.action_startFragment_to_homeFragment)
    }
}