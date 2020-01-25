package com.zhussain.pinboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhussain.pinboard.databinding.UserListItemBinding
import com.zhussain.pinboard.model.UsersProperty

class UserListAdapter :
    ListAdapter<UsersProperty, UserListAdapter.UsersPropertyViewHolder>(DiffCallback) {

    /**
     * The UsersPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [UsersProperty] information.
     */
    class UsersPropertyViewHolder(private var binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(usersProperty: UsersProperty) {
            binding.userProperty = usersProperty
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [UsersProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<UsersProperty>() {
        override fun areItemsTheSame(oldItem: UsersProperty, newItem: UsersProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UsersProperty, newItem: UsersProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.UsersPropertyViewHolder {
        return UsersPropertyViewHolder(UserListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: UserListAdapter.UsersPropertyViewHolder, position: Int) {
        val mUsersProperty = getItem(position)
        holder.bind(mUsersProperty)
    }

}