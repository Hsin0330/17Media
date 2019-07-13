package com.wind.githubuserssearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wind.githubuserssearch.R
import com.wind.githubuserssearch.data.UserInfo
import com.wind.githubuserssearch.databinding.ItemUserInfoBinding

class MainAdapter : PagedListAdapter<UserInfo, ViewHolder>(UserInfoDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserInfoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user_info,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position)!!)
    }
}

class ViewHolder(private val binding: ItemUserInfoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: UserInfo) {
        binding.userInfo = data
    }
}

object UserInfoDiff : DiffUtil.ItemCallback<UserInfo>() {
    override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo) = oldItem == newItem
}