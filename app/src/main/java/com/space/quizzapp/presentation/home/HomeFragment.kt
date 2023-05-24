package com.space.quizzapp.presentation.home

import androidx.navigation.fragment.findNavController
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentHomeBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.dialog.showTwoButtonDialog

class HomeFragment : BaseFragment() {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind() {
        setListeners()
        dialogListener()
    }

    private fun setListeners() {
        with(binding) {
            detailImageButton.setOnClickListener { navigateToFragment(R.id.action_homeFragment_to_detailsFragment) }
            homeRecyclerView.setRecyclerListener { navigateToFragment(R.id.action_homeFragment_to_questionsFragment) }
        }
    }

    private fun dialogListener() {
        binding.logOutImageView.setOnClickListener {
            showTwoButtonDialog(
                requireContext(),
                requireContext().getString(R.string.dialog_question),
                requireContext().getDrawable(R.drawable.bkg_yes_button)!!,
                requireContext().getDrawable(R.drawable.bkg_no_button)!!,
                positiveButtonAction = {
                    navigateToFragment(R.id.action_homeFragment_to_startFragment)
                },
                negativeButtonAction = {
                }
            )
        }
    }

    private fun navigateToFragment(destinationId: Int) {
        findNavController().navigate(destinationId)
    }
}
