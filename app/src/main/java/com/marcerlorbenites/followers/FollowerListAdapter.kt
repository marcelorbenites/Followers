package com.marcerlorbenites.followers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FollowerListAdapter(
    private val inflater: LayoutInflater,
    private val items: MutableList<Follower> = mutableListOf()
) : RecyclerView.Adapter<FollowerViewHolder>() {

    override fun getItemViewType(position: Int) = R.layout.item_follower

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(inflater.inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.setFollower(items[position])
    }

    fun setFollowers(followers: List<Follower>) {
        items.clear()
        items.addAll(followers)
        notifyDataSetChanged()
    }
}
