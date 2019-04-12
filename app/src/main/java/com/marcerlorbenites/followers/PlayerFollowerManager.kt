package com.marcerlorbenites.followers

import com.marcerlorbenites.followers.state.State
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PlayerFollowerManager(
    private val service: FollowerService,
    private val scheduler: Scheduler,
    private val publishScheduler: Scheduler,
    currentState: State<Followers> = State(State.Name.IDLE),
    private val disposable: CompositeDisposable = CompositeDisposable()
) : FollowerManager(currentState) {

    override fun setup() {
        loadFollowers()
    }

    override fun loadFollowers() {
        if (currentState.name != State.Name.LOADING) {
            moveToLoading()
            disposable.add(
                Single.fromCallable { Followers(service.getFollowers()) }
                    .subscribeOn(scheduler)
                    .observeOn(publishScheduler)
                    .subscribe(
                        { followers ->
                            moveToLoaded(followers)
                        },
                        {
                            moveToError()
                        }
                    ))
        } else {
            moveToLoading()
        }
    }

    override fun loadMoreFollowers() {
        if (currentState.name != State.Name.LOADING) {
            moveToLoading()
            disposable.add(
                Single.fromCallable {
                    val followers = currentState.value!!

                    if (followers.list.isNotEmpty()) {
                        val nextFollowers = service.getNextFollowers(followers.last!!.id)
                        val list = followers.list.toMutableList()
                        list.addAll(nextFollowers)
                        followers.copy(list = list)
                    } else {
                        followers
                    }
                }
                    .subscribeOn(scheduler)
                    .observeOn(publishScheduler)
                    .subscribe(
                        { followers ->
                            moveToLoaded(followers)
                        },
                        {
                            moveToError()
                        }
                    ))
        } else {
            moveToLoading()
        }
    }
}
