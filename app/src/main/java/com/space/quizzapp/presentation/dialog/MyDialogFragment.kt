package com.space.quizzapp.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.space.quizzapp.databinding.DialogLayoutBinding

class MyDialogFragment : DialogFragment() {
    private var _binding: DialogLayoutBinding? = null
    private val binding get() = _binding!!
    private var positiveButtonAction: (() -> Unit)? = null
    private var negativeButtonAction: (() -> Unit)? = null
    private var positiveButtonBackground: Drawable? = null
    private var negativeButtonBackground: Drawable? = null
    private var commonTextViewText: String? = null
    private var imageView: Drawable? = null
    private var collectedPointsText: String? = null
    private var closeText: String? = null
    private var buttonAction: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogLayoutBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        with(binding) {
            commonTextView.text = commonTextViewText
            confirmImageButton.background = positiveButtonBackground
            declineImageButton.background = negativeButtonBackground
            emojiImageView.background = imageView
            commonTextView.text = commonTextViewText
            collectedPointsTextView.text = collectedPointsText
            closeTextView.text = closeText
            closeTextView.setOnClickListener {
                buttonAction?.invoke()
                dismiss()
            }
            confirmImageButton.setOnClickListener {
                positiveButtonAction?.invoke()
                dismiss()
            }
            declineImageButton.setOnClickListener {
                negativeButtonAction?.invoke()
                dismiss()
            }
        }
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun twoButtonState(
            commonTextViewText: String,
            positiveButtonBackground: Drawable,
            negativeButtonBackground: Drawable,
            positiveButtonAction: () -> Unit,
            negativeButtonAction: () -> Unit
        ): MyDialogFragment {
            val fragment = MyDialogFragment()
            fragment.commonTextViewText = commonTextViewText
            fragment.positiveButtonBackground = positiveButtonBackground
            fragment.negativeButtonBackground = negativeButtonBackground
            fragment.positiveButtonAction = positiveButtonAction
            fragment.negativeButtonAction = negativeButtonAction
            return fragment
        }

        fun oneButtonState(
            imageView: Drawable,
            commonTextViewText: String,
            collectedPointsText: String,
            closeText: String,
            buttonAction: () -> Unit
        ): MyDialogFragment {
            val fragment = MyDialogFragment()
            fragment.commonTextViewText = commonTextViewText
            fragment.imageView = imageView
            fragment.collectedPointsText = collectedPointsText
            fragment.closeText = closeText
            fragment.buttonAction = buttonAction
            return fragment
        }
    }
}
