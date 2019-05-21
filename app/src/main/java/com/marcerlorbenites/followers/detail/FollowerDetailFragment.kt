package com.marcerlorbenites.followers.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcerlorbenites.followers.R
import kotlinx.android.synthetic.main.fragment_follower_detail.*

class FollowerDetailFragment : Fragment(), FollowerDetailView {

    companion object {
        const val IMAGE_LOADER_REFERENCE = "FollowerDetailFragment"
    }

    private val followerManager by lazy {
        container!!.followerManager
    }

    private val navigator by lazy {
        container!!.navigator
    }

    private val imageLoader by lazy {
        container!!.imageLoader
    }

    private val presenter by lazy {
        FollowerDetailPresenter(this, navigator)
    }

    private val backNavigationListener by lazy {
        View.OnClickListener { navigator.navigateBack() }
    }

    private var container: FollowerDetailContainer? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FollowerDetailContainer) {
            container = context
        } else {
            throw IllegalStateException("Context must implement ${FollowerDetailContainer::class.java.simpleName}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        followerManager.registerListener(presenter)
        toolbar.setNavigationOnClickListener(backNavigationListener)
    }

    override fun onPause() {
        toolbar.setNavigationOnClickListener(null)
        followerManager.unregisterListener(presenter)
        super.onPause()
    }

    override fun onDetach() {
        container = null
        super.onDetach()
    }

    override fun showClubPicture(clubPicture: String) {
        imageLoader.load(this.clubPicture, clubPicture, IMAGE_LOADER_REFERENCE)
    }

    override fun showProfilePicture(profilePicture: String) {
        imageLoader.load(this.profilePicture, profilePicture, IMAGE_LOADER_REFERENCE)
    }

    override fun showName(name: String) {
        this.name.text = name
    }
}
