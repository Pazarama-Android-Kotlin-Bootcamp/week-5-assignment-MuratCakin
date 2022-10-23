package com.muratcakin.userposts.ui.comments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.userposts.data.model.CommentDTO
import com.muratcakin.userposts.databinding.ItemCommentLayoutBinding
import com.muratcakin.userposts.ui.comments.CommentsFragment

class CommentsAdapter(
    commentsFragment: CommentsFragment,
    private val listener: OnCommentClickListener
    ) : ListAdapter<CommentDTO, CommentsAdapter.CommentViewHolder>(CommentsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ItemCommentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class CommentViewHolder(private val binding: ItemCommentLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentDTO, listener: OnCommentClickListener) {
            binding.dataHolder = comment
            binding.ivCommentImage.setOnClickListener {
                listener.onCommentClick(comment)
            }
            binding.executePendingBindings()
        }
    }

    class CommentsDiffUtil : DiffUtil.ItemCallback<CommentDTO>() {
        override fun areItemsTheSame(oldItem: CommentDTO, newItem: CommentDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentDTO, newItem: CommentDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnCommentClickListener {
    fun onCommentClick(comment: CommentDTO)
}