package com.muratcakin.userposts.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.userposts.data.model.PostDTO
import com.muratcakin.userposts.databinding.ItemPostLayoutBinding

class FavoritesAdapter(private val listener: OnFavoriteClickListener) :
    ListAdapter<PostDTO, FavoritesAdapter.FavoritesViewHolder>(FavoritesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            ItemPostLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }

    class FavoritesViewHolder(private val binding: ItemPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDTO, listener: OnFavoriteClickListener) {
            binding.dataHolder = post
            binding.ivPostImage.setOnClickListener {
                listener.onFavoriteClick(post)
            }
            binding.executePendingBindings()
        }
    }

    class FavoritesDiffUtil : DiffUtil.ItemCallback<PostDTO>() {
        override fun areItemsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem == newItem
        }

    }

}
interface OnFavoriteClickListener {
    fun onFavoriteClick(post: PostDTO)
}