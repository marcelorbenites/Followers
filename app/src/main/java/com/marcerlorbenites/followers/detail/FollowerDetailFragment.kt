package com.marcerlorbenites.followers.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcerlorbenites.followers.*
import com.marcerlorbenites.followers.list.Navigator
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_follower_detail.*

class FollowerDetailFragment : Fragment() {

    companion object {
        const val IMAGE_LOADER_REFERENCE = "FollowerDetailFragment"
    }

    private var container: FollowerDetailContainer? = null
    private var followerManager: FollowerManager? = null
    private var navigator: Navigator? = null
    private var imageLoader: ImageLoader? = null
    private var listener: StateListener<Followers>? = null
    private var backNavigationListener: View.OnClickListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FollowerDetailContainer) {
            container = context
        } else {
            throw IllegalStateException("Context must implement ${FollowerDetailContainer::class.java.simpleName}")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = object : StateListener<Followers> {
            override fun onStateUpdate(state: State<Followers>) {
                if (state.name == State.Name.LOADED) {
                    val followers = state.value!!
                    if (followers.followerSelected) {
                        showFollower(followers.selectedFollower!!)
                    } else {
                        navigator!!.navigateBack()
                    }
                }
            }
        }
        backNavigationListener = View.OnClickListener { navigator!!.navigateBack() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        followerManager = container!!.followerManager
        navigator = container!!.navigator
        imageLoader = container!!.imageLoader
    }

    override fun onResume() {
        super.onResume()
        followerManager!!.registerListener(listener!!)
        toolbar.setNavigationOnClickListener(backNavigationListener)
    }

    override fun onPause() {
        toolbar.setNavigationOnClickListener(null)
        followerManager!!.unregisterListener(listener!!)
        super.onPause()
    }

    override fun onDestroyView() {
        clearFindViewByIdCache()
        super.onDestroyView()
    }

    override fun onDestroy() {
        navigator = null
        listener = null
        backNavigationListener = null
        followerManager = null
        imageLoader = null
        super.onDestroy()
    }

    override fun onDetach() {
        container = null
        super.onDetach()
    }

    private fun showFollower(follower: Follower) {
        name.text = follower.fullName
        imageLoader!!.load(profilePicture, follower.picture, IMAGE_LOADER_REFERENCE)
        if (follower.hasClub) {
            imageLoader!!.load(clubPicture, follower.club!!.picture, IMAGE_LOADER_REFERENCE)
        }
    }
}
