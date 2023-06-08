package com.space.quizzapp.presentation.home.fragment

import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.lifecycleScope
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentHomeBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.dialog.fragment.QuizzDialogFragment
import com.space.quizzapp.presentation.home.viewmodel.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    override fun onBind(viewModel: HomeViewModel) {
        showUserInfo(viewModel)
        setListeners()
        dialogListener(viewModel)
    }

    private fun showUserInfo(viewModel: HomeViewModel) {
        viewModel.getActiveUsernames()
        lifecycleScope {
            viewModel.activeUsernames.collect {
                binding.greetingTextView.text =  getString(R.string.greeting_text, it)
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            detailImageButton.setOnClickListener { navigateTo(R.id.action_homeFragment_to_detailsFragment) }
            homeRecyclerView.setRecyclerListener { navigateTo(R.id.action_homeFragment_to_questionsFragment) }
        }
    }

    private fun dialogListener(viewModel: HomeViewModel) {
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
