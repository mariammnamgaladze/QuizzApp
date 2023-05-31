package com.space.quizzapp.presentation.home.fragment

import androidx.lifecycle.lifecycleScope
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentHomeBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.dialog.fragment.QuizzDialogFragment
import com.space.quizzapp.presentation.home.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeFragmentViewModel>() {

    override val viewModelClass: KClass<HomeFragmentViewModel>
        get() = HomeFragmentViewModel::class

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind(viewModel: HomeFragmentViewModel) {
        showUserInfo(viewModel)
        setListeners()
        dialogListener(viewModel)
    }

    private fun showUserInfo(viewModel: HomeFragmentViewModel) {
        viewModel.getActiveUsernames()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.activeUsernames.collect {
                val greetingText = getString(R.string.greeting_text, it)
                binding.greetingTextView.text = greetingText
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            detailImageButton.setOnClickListener { navigateTo(R.id.action_homeFragment_to_detailsFragment) }
            homeRecyclerView.setRecyclerListener { navigateTo(R.id.action_homeFragment_to_questionsFragment) }
        }
    }

    private fun dialogListener(viewModel: HomeFragmentViewModel) {
        binding.logOutImageView.setOnClickListener {
            QuizzDialogFragment.twoButtonState(
                requireContext().getString(R.string.dialog_log_out_question),
                requireContext().getDrawable(R.drawable.bkg_yes_button)!!,
                requireContext().getDrawable(R.drawable.bkg_no_button)!!,
                positiveButtonAction = {
                    viewModel.updateActiveStatus(isActive = false)
                    navigateTo(R.id.action_homeFragment_to_startFragment)
                },
                negativeButtonAction = {}
            ).show(parentFragmentManager, "TwoButtonDialog")
        }
    }
}
