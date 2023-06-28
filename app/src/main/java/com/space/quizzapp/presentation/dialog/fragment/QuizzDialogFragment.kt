package com.space.quizzapp.presentation.dialog.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import androidx.fragment.app.DialogFragment
import com.space.quizzapp.databinding.DialogLayoutBinding

class QuizzDialogFragment private constructor(
    private val dialogType: DialogType,
    private val commonTextViewText: String?,
    private val positiveButtonBackground: Drawable?,
    private val negativeButtonBackground: Drawable?,
    private val positiveButtonAction: (() -> Unit)?,
    private val negativeButtonAction: (() -> Unit)?,
    private val imageView: Drawable?,
    private val collectedPointsText: String?,
    private val closeText: String?,
    private val buttonAction: (() -> Unit)?
) : DialogFragment() {

    private var _binding: DialogLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogLayoutBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        setupDialogContent()
        setupButtonActions(builder)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private fun setupDialogContent() {
        binding.commonTextView.text = commonTextViewText
        binding.confirmImageButton.background = positiveButtonBackground
        binding.declineImageButton.background = negativeButtonBackground
        binding.emojiImageView.background = imageView
        binding.collectedPointsTextView.text = collectedPointsText
        binding.closeTextView.text = closeText
    }

    private fun setupButtonActions(builder: AlertDialog.Builder) {
        when (dialogType) {
            DialogType.TWO_BUTTON -> {
                binding.closeTextView.visibility = GONE
                binding.confirmImageButton.setOnClickListener {
                    positiveButtonAction?.invoke()
                    dismiss()
                }
                binding.declineImageButton.setOnClickListener {
                    negativeButtonAction?.invoke()
                    dismiss()
                }
                builder.setCancelable(false)
            }
            DialogType.ONE_BUTTON -> {
                binding.confirmImageButton.visibility = GONE
                binding.declineImageButton.visibility = GONE
                binding.closeTextView.setOnClickListener {
                    buttonAction?.invoke()
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class DialogType {
        TWO_BUTTON,
        ONE_BUTTON
    }

    companion object {
        fun createDialog(builder: DialogBuilder): QuizzDialogFragment {
            return QuizzDialogFragment(
                builder.dialogType,
                builder.commonTextViewText,
                builder.positiveButtonBackground,
                builder.negativeButtonBackground,
                builder.positiveButtonAction,
                builder.negativeButtonAction,
                builder.imageView,
                builder.collectedPointsText,
                builder.closeText,
                builder.buttonAction
            )
        }
    }

    class DialogBuilder(val dialogType: DialogType) {
        var commonTextViewText: String? = null
        var positiveButtonBackground: Drawable? = null
        var negativeButtonBackground: Drawable? = null
        var positiveButtonAction: (() -> Unit)? = null
        var negativeButtonAction: (() -> Unit)? = null
        var imageView: Drawable? = null
        var collectedPointsText: String? = null
        var closeText: String? = null
        var buttonAction: (() -> Unit)? = null

        fun setCommonTextViewText(text: String): DialogBuilder {
            this.commonTextViewText = text
            return this
        }

        fun setPositiveButtonBackground(background: Drawable): DialogBuilder {
            this.positiveButtonBackground = background
            return this
        }

        fun setNegativeButtonBackground(background: Drawable): DialogBuilder {
            this.negativeButtonBackground = background
            return this
        }

        fun setPositiveButtonAction(action: () -> Unit): DialogBuilder {
            this.positiveButtonAction = action
            return this
        }

        fun setNegativeButtonAction(action: () -> Unit): DialogBuilder {
            this.negativeButtonAction = action
            return this
        }

        fun setImageView(image: Drawable): DialogBuilder {
            this.imageView = image
            return this
        }

        fun setCollectedPointsText(text: String): DialogBuilder {
            this.collectedPointsText = text
            return this
        }

        fun setCloseText(text: String): DialogBuilder {
            this.closeText = text
            return this
        }

        fun setButtonAction(action: () -> Unit): DialogBuilder {
            this.buttonAction = action
            return this
        }

        fun build(): QuizzDialogFragment {
            return createDialog(this)
        }
    }
}
