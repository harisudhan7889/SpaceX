package com.spacex.launch.list.repository

import android.arch.lifecycle.LiveData
import com.spacex.launch.common.model.LaunchDao
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.list.endpoint.LauncheListApi
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.Executors

/**
 * Class where network/database operation takes place.
 *
 * 1. Api service to get all past spacex launches.
 * 2. Store the launches in db.
 *
 * @author Hari Hara Sudhan.N
 */
class LaunchListRepository(private val lauchListApi: LauncheListApi,
                           private val launchDao: LaunchDao) {

    /**
     * Retrieves launches from database.
     */
    fun getPastLaunchesFromDB(): LiveData<List<LaunchData>> {
        return launchDao.getPastLaunches()
    }

    /**
     * Retrieves launches from remote.
     */
    fun getPastLaunchesFromRemote() {
        lauchListApi.getPastLaunches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it.isNotEmpty() }
                .subscribe(object : MaybeObserver<ArrayList<LaunchData>> {

                    override fun onSuccess(launches: ArrayList<LaunchData>) {
                        Timber.d("Past launch service is success.")
                        cacheInDatabase(launches)
                    }

                    override fun onComplete() {
                        Timber.d("Observer method onComplete is called.")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Timber.d("Past launch service is subscribed to a observer.")
                    }

                    override fun onError(error: Throwable) {
                        Timber.e(error, "Past launch service throws an error.")
                    }

                })
    }

    /**
     * Handles database cache operation.
     */
    fun cacheInDatabase(launches: ArrayList<LaunchData>) {
        // Cannot update the DB in main thread, so created a background thread
        val executor = Executors.newFixedThreadPool(1)
        val worker = Runnable { launchDao.insertPastLaunches(launches) }
        executor.execute(worker)
    }
}