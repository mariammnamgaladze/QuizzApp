package com.space.quizzapp.presentation.home.fragment

import HomeAdapter
import android.view.View
import androidx.core.content.ContextCompat
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.common.extensions.convertToDecimals
import com.space.quizzapp.common.extensions.lifecycleScope
import com.space.quizzapp.common.extensions.setColoredTextWithPrefix
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
        collectAsync(viewModel.quizItems) {
            homeAdapter.submitList(it)
        }
        collectAsync(viewModel.isLoading) { isLoading ->
            binding.progressBar.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        collectAsync(viewModel.error) {
            it?.let {
                val dialogFragment =
                    QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.ONE_BUTTON)
                        .setCommonTextViewText((requireContext().getString(R.string.no_internet)))
                        .setCloseText((requireContext().getString(R.string.close)))
                        .build()
                dialogFragment.show(parentFragmentManager, null)
            }
        }
    }

    private fun showUserInfo() {
        viewModel.getActiveUsernames()
        collectAsync(viewModel.activeUsernames) {
            binding.greetingTextView.text = getString(R.string.greeting_text, it?.username)
            binding.gpaTV.setColoredTextWithPrefix("GPA - ",it?.gpa!!.convertToDecimals(1),
                ContextCompat.getColor(requireContext(),R.color.yellow_primary))
        }
    }

    private fun setListeners() {
        with(binding) {
            root.setOnClickListener {
                viewModel.navigateTo(HomeFragmentDirections.actionHomeFragmentToDetailsFragment())
            }
        }
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(item: QuizItemUIModel) {
                viewModel.navigateToQuiz(item)
            }
        })
    }

    private fun dialogListener() {
        binding.logOutImageView.setOnClickListener {
            val dialogFragment =
                QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.TWO_BUTTON)
                    .setCommonTextViewText(requireContext().getString(R.string.dialog_log_out_question))
                    .setPositiveButtonBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bkg_yes_button)!!)
                    .setNegativeButtonBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bkg_no_button)!!)
                    .setPositiveButtonAction {
                        viewModel.updateActiveStatus(isActive = false)
                        viewModel.navigate(HomeFragmentDirections.actionHomeFragmentToStartFragment())
                    }
                    .build()
            dialogFragment.show(parentFragmentManager, null)
        }
    }
}
