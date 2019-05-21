package com.marcerlorbenites.followers.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcerlorbenites.followers.R
import kotlinx.android.synthetic.main.fragment_follower_list.*


class FollowerListFragment : Fragment(), FollowerListView {

    companion object {
        const val IMAGE_LOADER_REFERENCE = "FollowerListFragment"
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
        FollowerListPresenter(this, getString(R.string.fragment_follower_list_no_team))
    }

    private val scrollController by lazy {
        container!!.scrollController
    }

    private val scrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollController.onScroll(
                    recyclerView.canScrollVertically(1),
                    adapter!!.itemCount,
                    layoutManager!!.findLastVisibleItemPosition()
                )
            }
        }
    }

    private val selectListener by lazy {
        object : FollowerListAdapter.OnFollowerClickListener {
            override fun onFollowerClick(followerItem: FollowerItemViewModel) {
                followerManager.selectFollower(followerItem.id)
                navigator.navigateToFollowerDetailView()
            }
        }
    }

    private val retryListener by lazy {
        View.OnClickListener { followerManager.loadFollowers() }
    }

    private var container: FollowerListContainer? = null
    private var adapter: FollowerListAdapter? = null
    private var layoutManager: LinearLayoutManager? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FollowerListContainer) {
            container = context
        } else {
            throw IllegalStateException("Context must implement ${FollowerListContainer::class.java.simpleName}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FollowerListAdapter(
            LayoutInflater.from(context),
            imageLoader,
            IMAGE_LOADER_REFERENCE
        )
        layoutManager = LinearLayoutManager(context)
        followerList.layoutManager = layoutManager
        followerList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter!!.clickListener = selectListener
        followerManager.registerListener(presenter)
        followerList.addOnScrollListener(scrollListener)
        retryButton.setOnClickListener(retryListener)
    }

    override fun onPause() {
        adapter!!.clickListener = null
        followerManager.unregisterListener(presenter)
        followerList.removeOnScrollListener(scrollListener)
        retryButton.setOnClickListener(null)
        super.onPause()
    }

    override fun onDestroyView() {
        adapter = null
        layoutManager = null
        imageLoader.cancel(IMAGE_LOADER_REFERENCE)
        super.onDestroyView()
    }

    override fun onDetach() {
        container = null
        super.onDetach()
    }

    override fun showMainLoading() {
        mainLoading.visibility = View.VISIBLE
    }

    override fun hideMainLoading() {
        mainLoading.visibility = View.GONE
    }

    override fun showListLoading() {
        listLoading.visibility = View.VISIBLE
    }

    override fun hideListLoading() {
        listLoading.visibility = View.GONE
    }

    override fun showFollowers(followerList: FollowerListViewModel) {
        this.followerList.visibility = View.VISIBLE
        adapter!!.setFollowers(followerList.list)
    }

    override fun hideFollowers() {
        followerList.visibility = View.GONE
    }

    override fun showUnknownError() {
        errorText.setText(R.string.fragment_follower_list_unknown_error)
        errorText.visibility = View.VISIBLE
    }

    override fun showNetworkError() {
        errorText.setText(R.string.fragment_follower_list_network_error)
        errorText.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorText.visibility = View.GONE
    }

    override fun showRetry() {
        retryButton.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        retryButton.visibility = View.GONE
    }
}
