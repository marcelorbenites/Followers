package com.marcerlorbenites.followers.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcerlorbenites.followers.ImageLoader
import com.marcerlorbenites.followers.R

class FollowerViewHolder(
    view: View,
    private val imageLoader: ImageLoader,
    private val imageLoaderReference: String,
    private val clickListener: FollowerListAdapter.OnFollowerClickListener?
) : RecyclerView.ViewHolder(view) {

    private val name = itemView.findViewById<TextView>(R.id.name)
    private val clubName = itemView.findViewById<TextView>(R.id.clubName)
    private val picture = itemView.findViewById<ImageView>(R.id.picture)

    fun setFollower(followerItem: FollowerItemViewModel) {

        itemView.setOnClickListener {
            clickListener?.onFollowerClick(followerItem)
        }

        name.text = followerItem.fullName
        imageLoader.load(picture, followerItem.picture, imageLoaderReference)
        clubName.text = followerItem.clubName
    }
}
