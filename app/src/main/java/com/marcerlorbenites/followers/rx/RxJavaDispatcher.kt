package com.marcerlorbenites.followers.rx

import com.marcerlorbenites.followers.state.Dispatcher
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class RxJavaDispatcher<T>(
    private val scheduler: Scheduler,
    private val publishScheduler: Scheduler,
    private val disposable: CompositeDisposable = CompositeDisposable()
) : Dispatcher<T> {
    override fun dispatch(function: () -> T, success: (T) -> Unit, error: (Throwable) -> Unit) {
        disposable.add(
            Single.fromCallable(function)
                .subscribeOn(scheduler)
                .observeOn(publishScheduler)
                .subscribe(
                    success,
                    error
                )
        )
    }
}
