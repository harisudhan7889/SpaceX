package com.spacex.launch.common.archcomponent

import io.reactivex.SingleObserver

/**
 * @author Hari Hara Sudhan.N
 */
abstract class UseCase<T1, T2> {
    abstract fun invoke(data: T1? = null)
    abstract fun setObserver(singleObserver: SingleObserver<List<T2>>?)
}