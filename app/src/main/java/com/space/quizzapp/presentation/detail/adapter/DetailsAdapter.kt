package com.space.quizzapp.presentation.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.quizzapp.common.extensions.setImage
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.SubjectLayoutItemBinding
import com.space.quizzapp.presentation.home.adapter.DiffCallback
import com.space.quizzapp.presentation.model.local.UserSubjectUIModel

class DetailsAdapter  :
    ListAdapter<UserSubjectUIModel, DetailsAdapter.UserSubjectViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSubjectViewHolder {
        return UserSubjectViewHolder(parent.viewBinding(SubjectLayoutItemBinding::inflate))
    }

    override fun onBindViewHolder(holder: UserSubjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class UserSubjectViewHolder(private val binding: SubjectLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserSubjectUIModel) {
            with(binding) {
                subjectNameTextView.text = item.quizTitle
                descriptionTextView.text = item.quizDescription
                SubjectImageView.setImage(item.quizIcon)
                imageButton.icon = null
                imageButton.text = item.collectedPoints.toString()
            }
        }
    }
}
