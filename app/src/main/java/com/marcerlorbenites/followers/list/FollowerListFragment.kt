package com.marcerlorbenites.followers.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcerlorbenites.followers.FollowerManager
import com.marcerlorbenites.followers.Followers
import com.marcerlorbenites.followers.ImageLoader
import com.marcerlorbenites.followers.R
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_follower_list.*


class FollowerListFragment : Fragment() {

    companion object {
        const val IMAGE_LOADER_REFERENCE = "FollowerListFragment"
    }

    private var container: FollowerListViewContainer? = null
    private var followerManager: FollowerManager? = null
    private var imageLoader: ImageLoader? = null
    private var listener: StateListener<Followers>? = null
    private var scrollListener: RecyclerView.OnScrollListener? = null
    private var retryListener: View.OnClickListener? = null
    private var adapter: FollowerListAdapter? = null
    private var layoutManager: LinearLayoutManager? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FollowerListViewContainer) {
            container = context
        } else {
            throw IllegalStateException("Context must implement ${FollowerListViewContainer::class.java.simpleName}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = object : StateListener<Followers> {
            override fun onStateUpdate(state: State<Followers>) {
                when (state.name) {
                    State.Name.IDLE, State.Name.LOADING -> {
                        if (state.value == null) {
                            showMainLoading()
                            hideListLoading()
                            hideFollowers()
                        } else {
                            showListLoading()
                            hideMainLoading()
                        }
                        hideError()
                        hideRetry()
                    }
                    State.Name.LOADED -> {
                        hideMainLoading()
                        hideListLoading()
                        showFollowers(state.value!!)
                        hideError()
                        hideRetry()
                    }
                    State.Name.ERROR -> {
                        showError()
                        showRetry()
                        hideMainLoading()
                        hideListLoading()
                        hideFollowers()
                    }
                }
            }
        }
        scrollListener = object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!followerList.canScrollVertically(1)) {
                    followerManager!!.loadMoreFollowers()
                }
            }
        }

        retryListener = View.OnClickListener { followerManager!!.loadFollowers() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        followerManager = container!!.followerManager
        imageLoader = container!!.imageLoader
        adapter = FollowerListAdapter(
            LayoutInflater.from(context),
            imageLoader!!,
            IMAGE_LOADER_REFERENCE,
            getString(R.string.fragment_follower_list_no_team)
        )
        layoutManager = LinearLayoutManager(context)
        followerList.layoutManager = layoutManager
        followerList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        followerManager!!.registerListener(listener!!)
        followerList.addOnScrollListener(scrollListener!!)
        retryButton.setOnClickListener(retryListener!!)
    }

    override fun onPause() {
        followerManager!!.unregisterListener(listener!!)
        followerList.removeOnScrollListener(scrollListener!!)
        retryButton.setOnClickListener(null)
        super.onPause()
    }

    override fun onDestroyView() {
        adapter = null
        layoutManager = null
        imageLoader!!.cancel(IMAGE_LOADER_REFERENCE)
        followerList.removeOnScrollListener(scrollListener!!)
        clearFindViewByIdCache()
        super.onDestroyView()
    }

    override fun onDestroy() {
        listener = null
        scrollListener = null
        followerManager = null
        imageLoader = null
        retryListener = null
        super.onDestroy()
    }

    override fun onDetach() {
        container = null
        super.onDetach()
    }

    private fun showMainLoading() {
        mainLoading.visibility = View.VISIBLE
    }

    private fun hideMainLoading() {
        mainLoading.visibility = View.GONE
    }

    private fun showListLoading() {
        listLoading.visibility = View.VISIBLE
    }

    private fun hideListLoading() {
        listLoading.visibility = View.GONE
    }

    private fun showFollowers(followers: Followers) {
        followerList.visibility = View.VISIBLE
        val viewsState = layoutManager!!.onSaveInstanceState()
        adapter!!.setFollowers(followers.list)
        layoutManager!!.onRestoreInstanceState(viewsState)
    }

    private fun hideFollowers() {
        followerList.visibility = View.GONE
    }

    private fun showError() {
        errorText.visibility = View.VISIBLE
    }

    private fun hideError() {
        errorText.visibility = View.GONE
    }

    private fun showRetry() {
        retryButton.visibility = View.VISIBLE
    }

    private fun hideRetry() {
        retryButton.visibility = View.GONE
    }


}
