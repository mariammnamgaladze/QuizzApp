package com.space.quizzapp.presentation.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import com.space.quizzapp.databinding.DialogLayoutBinding

fun showSingleButtonDialog(
    context: Context,
    ImageView: Drawable,
    commonTextViewText: String,
    collectedPointsText: String,
    closeText: String,
    buttonAction: () -> Unit
) {
    val binding = DialogLayoutBinding.inflate(LayoutInflater.from(context))
    val alertDialogBuilder = AlertDialog.Builder(context)
        .setView(binding.root)
    val alertDialog = alertDialogBuilder.create()

    with(binding) {
        emojiImageView.background = ImageView
        commonTextView.text = commonTextViewText
        collectedPointsTextView.text = collectedPointsText
        closeTextView.text = closeText
        closeTextView.setOnClickListener {
            buttonAction.invoke()
            alertDialog.dismiss()
        }
    }
    alertDialog.show()
}

fun showTwoButtonDialog(
    context: Context,
    commonTextViewText: String,
    positiveButtonBackground: Drawable,
    negativeButtonBackground: Drawable,
    positiveButtonAction: () -> Unit,
    negativeButtonAction: () -> Unit
) {
    val binding = DialogLayoutBinding.inflate(LayoutInflater.from(context))
    val alertDialogBuilder = AlertDialog.Builder(context)
        .setView(binding.root)
    val alertDialog = alertDialogBuilder.create()

    with(binding) {
        commonTextView.text = commonTextViewText
        confirmImageButton.background = positiveButtonBackground
        declineImageButton.background = negativeButtonBackground
        confirmImageButton.setOnClickListener {
            positiveButtonAction.invoke()
            alertDialog.dismiss()
        }
        declineImageButton.setOnClickListener {
            negativeButtonAction.invoke()
            alertDialog.dismiss()
        }
    }
    alertDialog.show()
}
