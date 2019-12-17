package com.spacex.launch.common.rx

import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * @author Hari Hara Sudhan.N
 */
open class SimpleCompletableObserver: CompletableObserver {

    override fun onComplete() {
        Timber.d("Observer completes its listening.")
    }

    override fun onSubscribe(d: Disposable) {
        Timber.d("Subscribed to a producer.")
    }

    override fun onError(e: Throwable) {
        Timber.e(e.toString())
    }
}