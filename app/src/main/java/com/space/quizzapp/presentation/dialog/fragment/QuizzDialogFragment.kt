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

class QuizzDialogFragment constructor(
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


    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogLayoutBinding.inflate(LayoutInflater.from(requireContext()))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        setupDialogContent()
        setupButtonActions(builder)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        return dialog
    }

    private fun setupDialogContent() {
        with(binding) {
            commonTextView.text = commonTextViewText
            confirmImageButton.background = positiveButtonBackground
            declineImageButton.background = negativeButtonBackground
            emojiImageView.background = imageView
            collectedPointsTextView.text = collectedPointsText
            closeTextView.text = closeText
        }
    }

    private fun setupButtonActions(builder: AlertDialog.Builder) {
        when (dialogType) {
            DialogType.TWO_BUTTON -> {
                with(binding) {
                    closeTextView.visibility = GONE
                    confirmImageButton.setOnClickListener {
                        positiveButtonAction?.invoke()
                        dismiss()
                    }
                    declineImageButton.setOnClickListener {
                        negativeButtonAction?.invoke()
                        dismiss()
                    }
                }
            }

            DialogType.ONE_BUTTON -> {
                with(binding) {
                    confirmImageButton.visibility = GONE
                    declineImageButton.visibility = GONE
                    closeTextView.setOnClickListener {
                        buttonAction?.invoke()
                        dismiss()
                    }
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
            with(builder) {
                return QuizzDialogFragment(
                    dialogType,
                    commonTextViewText,
                    positiveButtonBackground,
                    negativeButtonBackground,
                    positiveButtonAction,
                    negativeButtonAction,
                    imageView,
                    collectedPointsText,
                    closeText,
                    buttonAction
                )
            }
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
