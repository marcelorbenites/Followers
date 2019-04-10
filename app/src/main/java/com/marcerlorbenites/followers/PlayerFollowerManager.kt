package com.marcerlorbenites.followers

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PlayerFollowerManager(
    private val service: FollowerService,
    private val scheduler: Scheduler,
    private val publishScheduler: Scheduler,
    private val disposable: CompositeDisposable = CompositeDisposable()
) : FollowerManager() {

    override fun setup() {
        loadFollowers()
    }

    private fun loadFollowers() {
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
    }
}
