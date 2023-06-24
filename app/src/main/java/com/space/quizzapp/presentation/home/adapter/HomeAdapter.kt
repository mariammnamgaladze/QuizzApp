import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.quizzapp.common.extensions.setImage
import com.space.quizzapp.common.extensions.viewBinding
import com.space.quizzapp.databinding.SubjectLayoutItemBinding
import com.space.quizzapp.presentation.home.adapter.DiffCallback
import com.space.quizzapp.presentation.model.remote.QuizItemUIModel

class HomeAdapter :
    ListAdapter<QuizItemUIModel, HomeAdapter.QuizSubjectViewHolder>(DiffCallback()) {

    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizSubjectViewHolder {
        return QuizSubjectViewHolder(parent.viewBinding(SubjectLayoutItemBinding::inflate))
    }

    override fun onBindViewHolder(holder: QuizSubjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(item)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class QuizSubjectViewHolder(private val binding: SubjectLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QuizItemUIModel) {
            with(binding) {
                subjectNameTextView.text = item.quizTitle
                descriptionTextView.text = item.quizDescription
                SubjectImageView.setImage(item.quizIcon)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: QuizItemUIModel)
    }

}
