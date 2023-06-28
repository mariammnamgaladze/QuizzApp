package com.space.quizzapp.presentation.detail.fragment

import android.view.View
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.collectAsync
import com.space.quizzapp.common.extensions.lifecycleScope
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentDetailsBinding
import com.space.quizzapp.presentation.base.fragment.BaseFragment
import com.space.quizzapp.presentation.detail.adapter.DetailsAdapter
import com.space.quizzapp.presentation.detail.viewmodel.DetailsViewModel
import com.space.quizzapp.presentation.dialog.fragment.QuizzDialogFragment
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    private val detailsAdapter by lazy {
        DetailsAdapter()
    }
    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    override val layout: Int
        get() = R.layout.fragment_details

    override fun onBind() {
        observer()
        setUpRecycler()
        setListeners()
    }

    private fun setUpRecycler() {
        binding.detailRecyclerView.apply {
            adapter = detailsAdapter
        }
        lifecycleScope {
            viewModel.getUserSubject()
        }
    }

    private fun observer() {
        collectAsync(viewModel.subjectsItem) {
            if (it.isEmpty()) {
                binding.noPointTextView.visibility = View.VISIBLE
            } else {
                detailsAdapter.submitList(it)
                binding.noPointTextView.visibility = View.INVISIBLE
            }
        }
    }

    private fun setListeners() {
        binding.backImageButton.setOnClickListener {
            viewModel.navigateTo(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
        binding.logOutImageButton.setOnClickListener {
            val dialogFragment =
                QuizzDialogFragment.DialogBuilder(QuizzDialogFragment.DialogType.TWO_BUTTON)
                    .setCommonTextViewText(requireContext().getString(R.string.dialog_log_out_question))
                    .setPositiveButtonBackground(requireContext().getDrawable(R.drawable.bkg_yes_button)!!)
                    .setNegativeButtonBackground(requireContext().getDrawable(R.drawable.bkg_no_button)!!)
                    .setPositiveButtonAction {
                        viewModel.updateActiveStatus(isActive = false)
                        viewModel.navigateTo((DetailsFragmentDirections.actionDetailsFragmentToStartFragment()))
                    }
                    .build()
            dialogFragment.show(parentFragmentManager, null)
        }
    }
}
