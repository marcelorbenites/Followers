package com.marcerlorbenites.followers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_follower_list.*

class FollowerListFragment : Fragment() {

    private var container: FollowerListViewContainer? = null
    private var followerManager: FollowerManager? = null
    private var listener: StateListener<Followers>? = null
    private var adapter: FollowerListAdapter? = null

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
                if (state.name == State.Name.LOADED) {
                    val followers = state.value!!
                    adapter!!.setFollowers(followers.list)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FollowerListAdapter(LayoutInflater.from(context))
        followerList.layoutManager = LinearLayoutManager(context)
        followerList.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        followerManager = container!!.followerManager
    }

    override fun onResume() {
        super.onResume()
        followerManager!!.registerListener(listener!!)
    }

    override fun onPause() {
        followerManager!!.unregisterListener(listener!!)
        super.onPause()
    }

    override fun onDestroyView() {
        adapter = null
        clearFindViewByIdCache()
        super.onDestroyView()
    }

    override fun onDestroy() {
        listener = null
        followerManager = null
        super.onDestroy()
    }

    override fun onDetach() {
        container = null
        super.onDetach()
    }

}
