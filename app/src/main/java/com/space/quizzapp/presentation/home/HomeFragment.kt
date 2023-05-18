package com.space.quizzapp.presentation.home

import androidx.navigation.fragment.findNavController
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentHomeBinding
import com.space.quizzapp.presentation.base.BaseFragment

class HomeFragment : BaseFragment() {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind() {
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            detailImageButton.setOnClickListener { navigateToFragment(R.id.action_homeFragment_to_detailsFragment) }
            logOutImageView.setOnClickListener { navigateToFragment(R.id.action_homeFragment_to_startFragment) }
            homeRecyclerView.setRecyclerListener { navigateToFragment(R.id.action_homeFragment_to_questionsFragment) }
        }
    }

    private fun navigateToFragment(destinationId: Int) {
        findNavController().navigate(destinationId)
    }
}
