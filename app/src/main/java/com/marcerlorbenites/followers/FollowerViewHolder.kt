package com.marcerlorbenites.followers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FollowerViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val name = itemView.findViewById<TextView>(R.id.name)
    private val clubName = itemView.findViewById<TextView>(R.id.clubName)
    private val picture = itemView.findViewById<ImageView>(R.id.picture)

    fun setFollower(follower: Follower) {
        name.text = follower.fullName
        clubName.text = follower.club.name
    }
}
