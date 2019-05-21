package com.marcerlorbenites.followers.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcerlorbenites.followers.ImageLoader
import com.marcerlorbenites.followers.R

class FollowerListAdapter(
    private val inflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val imageLoaderReference: String,
    private val items: MutableList<FollowerItemViewModel> = mutableListOf()
) : RecyclerView.Adapter<FollowerViewHolder>() {

    var clickListener: OnFollowerClickListener? = null

    override fun getItemViewType(position: Int) = R.layout.item_follower

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(
            inflater.inflate(viewType, parent, false),
            imageLoader,
            imageLoaderReference,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.setFollower(items[position])
    }

    fun setFollowers(followerItems: List<FollowerItemViewModel>) {
        items.clear()
        items.addAll(followerItems)
        notifyDataSetChanged()
    }

    interface OnFollowerClickListener {
        fun onFollowerClick(followerItem: FollowerItemViewModel)
    }
}

