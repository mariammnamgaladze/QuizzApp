package com.space.quizzapp.presentation.home
import com.space.quizzapp.R
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.FragmentHomeBinding
import com.space.quizzapp.presentation.base.BaseFragment
import com.space.quizzapp.presentation.dialog.MyDialogFragment

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
            detailImageButton.setOnClickListener { navigateTo(R.id.action_homeFragment_to_detailsFragment) }
            homeRecyclerView.setRecyclerListener { navigateTo(R.id.action_homeFragment_to_questionsFragment) }
        }
    }

    private fun dialogListener() {
        binding.logOutImageView.setOnClickListener {
           /* showTwoButtonDialog(
                requireContext(),
                requireContext().getString(R.string.dialog_question),
                requireContext().getDrawable(R.drawable.bkg_yes_button)!!,
                requireContext().getDrawable(R.drawable.bkg_no_button)!!,
                positiveButtonAction = {
                    navigateTo(R.id.action_homeFragment_to_startFragment)
                },
                negativeButtonAction = {
                }
            )*/
            MyDialogFragment.twoButtonState(
                requireContext().getString(R.string.dialog_question),
                requireContext().getDrawable(R.drawable.bkg_yes_button)!!,
                requireContext().getDrawable(R.drawable.bkg_no_button)!!,
                positiveButtonAction = {
                    navigateTo(R.id.action_homeFragment_to_startFragment)
                },
                negativeButtonAction = {}
            ).show(parentFragmentManager, "TwoButtonDialog")
        }
    }

}
