package com.muratcakin.userposts.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muratcakin.userposts.data.model.Users
import com.muratcakin.userposts.data.model.UsersDTO
import com.muratcakin.userposts.databinding.ItemUserLayoutBinding

class UsersAdapter : ListAdapter<UsersDTO, UsersAdapter.UserViewHolder>(UsersDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(private val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: UsersDTO) {
            binding.dataHolderUser = users
            binding.executePendingBindings()
        }
    }

    class UsersDiffUtil : DiffUtil.ItemCallback<UsersDTO>() {
        override fun areItemsTheSame(oldItem: UsersDTO, newItem: UsersDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersDTO, newItem: UsersDTO): Boolean {
            return oldItem == newItem
        }

    }

}