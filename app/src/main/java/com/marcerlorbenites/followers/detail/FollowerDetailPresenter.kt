package com.marcerlorbenites.followers.detail

import com.marcerlorbenites.followers.Error
import com.marcerlorbenites.followers.Followers
import com.marcerlorbenites.followers.navigator.FragmentNavigator
import com.marcerlorbenites.followers.state.State
import com.marcerlorbenites.followers.state.StateListener

class FollowerDetailPresenter(
    private val view: FollowerDetailView,
    private val navigator: FragmentNavigator
) : StateListener<Followers, Error> {
    override fun onStateUpdate(state: State<Followers, Error>) {
        if (state.name == State.Name.LOADED) {
            val followers = state.value!!
            val selected = followers.followerSelected
            if (selected) {
                val selectedFollower = followers.selectedFollower!!

                view.showName(selectedFollower.fullName)
                view.showProfilePicture(selectedFollower.picture)

                if (selectedFollower.hasClub) {
                    view.showClubPicture(selectedFollower.club!!.picture)
                }
            } else {
                navigator.navigateBack()
            }
        }
    }
}
