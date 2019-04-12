package com.marcerlorbenites.followers.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcerlorbenites.followers.Follower
import com.marcerlorbenites.followers.ImageLoader
import com.marcerlorbenites.followers.R

class FollowerListAdapter(
    private val inflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val imageLoaderReference: String,
    private val noClubText: String,
    private val items: MutableList<Follower> = mutableListOf()
) : RecyclerView.Adapter<FollowerViewHolder>() {

    var clickListener: OnFollowerClickListener? = null

    override fun getItemViewType(position: Int) = R.layout.item_follower

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(
            inflater.inflate(viewType, parent, false),
            imageLoader,
            imageLoaderReference,
            noClubText,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.setFollower(items[position])
    }

    fun setFollowers(followers: List<Follower>) {
        items.clear()
        items.addAll(followers)
        notifyDataSetChanged()
    }

    interface OnFollowerClickListener {
        fun onFollowerClick(follower: Follower)
    }
}

