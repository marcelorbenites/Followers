package com.marcerlorbenites.followers.list

import com.marcerlorbenites.followers.Error
import com.marcerlorbenites.followers.Followers
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener

class FollowerListPresenter(private val view: FollowerListView, private val noClubText: String): StateListener<Followers, Error> {
    override fun onStateUpdate(state: State<Followers, Error>) {
        when (state.name) {
            State.Name.IDLE, State.Name.LOADING -> {
                if (state.value == null) {
                    view.showMainLoading()
                    view.hideListLoading()
                    view.hideFollowers()
                } else {
                    view.showListLoading()
                    view.hideMainLoading()
                }
                view.hideError()
                view.hideRetry()
            }
            State.Name.LOADED -> {
                view.hideMainLoading()
                view.hideListLoading()
                view.showFollowers(FollowerListViewModel(state.value!!, noClubText))
                view.hideError()
                view.hideRetry()
            }
            State.Name.ERROR -> {

                val error = state.error!!

                if (error.isNetwork()) {
                    view.showNetworkError()
                } else {
                    view.showUnknownError()
                }
                view.showRetry()
                view.hideMainLoading()
                view.hideListLoading()
                view.hideFollowers()
            }
        }
    }
}
