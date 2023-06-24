package com.space.quizzapp.presentation.home.fragment

import HomeAdapter
import android.view.View
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.lifecycleScope
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentHomeBinding
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.dialog.fragment.QuizzDialogFragment
import com.space.quizzapp.presentation.home.viewmodel.HomeViewModel
import com.space.quizzapp.presentation.model.remote.QuizItemUIModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layout: Int
        get() = R.layout.fragment_home

    private val homeAdapter by lazy {
        HomeAdapter()
    }

    override fun onBind() {
        showUserInfo()
        setListeners()
        dialogListener()
        observer()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.homeRecyclerView.apply {
            adapter = homeAdapter
        }
        lifecycleScope {
            viewModel.getQuiz()
        }
    }

    private fun observer() {
        lifecycleScope {
            viewModel.quizItems.collect {
                homeAdapter.submitList(it)
            }
        }
        lifecycleScope {
            viewModel.isLoading.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
        }
    }

    private fun showUserInfo() {
        viewModel.getActiveUsernames()
        lifecycleScope {
            viewModel.activeUsernames.collect {
                binding.greetingTextView.text = getString(R.string.greeting_text, it?.username)
                binding.gpaTV.text = getString(R.string.gpa, it?.gpa)

            }
        }
    }

    private fun setListeners() {
        with(binding) {
            detailImageButton.setOnClickListener { viewModel.navigateTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment()) }
        }
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(item: QuizItemUIModel) {
                viewModel.navigateToQuiz(item)
            }
        })
    }

    private fun dialogListener() {
        binding.logOutImageView.setOnClickListener {
            QuizzDialogFragment.twoButtonState(
                requireContext().getString(R.string.dialog_log_out_question),
                requireContext().getDrawable(R.drawable.bkg_yes_button)!!,
                requireContext().getDrawable(R.drawable.bkg_no_button)!!,
                positiveButtonAction = {
                    viewModel.updateActiveStatus(isActive = false)
                    viewModel.navigateTo(HomeFragmentDirections.actionHomeFragmentToStartFragment())
                }
            ) {}.show(parentFragmentManager, "")
        }
    }
}
