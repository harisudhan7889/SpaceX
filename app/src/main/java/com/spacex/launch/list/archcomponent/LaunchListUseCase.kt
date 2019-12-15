package com.spacex.launch.list.archcomponent

import android.annotation.SuppressLint
import com.spacex.launch.common.archcomponent.UseCase
import com.spacex.launch.common.model.LaunchDao
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.list.endpoint.LauncheListApi
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Class where network/database operation takes place.
 *
 * 1. Api service to get all past spacex launches.
 * 2. Store the launches in db.
 *
 * @author Hari Hara Sudhan.N
 */
class LaunchListUseCase @Inject constructor(private val launchListApi: LauncheListApi,
                                            private val launchDao: LaunchDao): UseCase<Unit, LaunchData>() {
    private var singleObserver: SingleObserver<List<LaunchData>>? = null
    private var totalPastLaunches = 0

    @SuppressLint("CheckResult")
    override fun invoke(data: Unit?) {
        launchDao
                .getPastLaunchCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::noOfPastLaunches)
    }

    /**
     * Setter method for the observer.
     */
    override fun setObserver(singleObserver: SingleObserver<List<LaunchData>>?) {
        this.singleObserver = singleObserver
    }

    /**
     * Retrieves launches from database.
     */
    private fun getPastLaunchesFromDB() {
        singleObserver?.let {
        launchDao.getPastLaunches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(it)
        }
    }

    /**
     * Retrieves launches from remote server.
     */
    private fun getPastLaunchesFromRemote() {
        singleObserver?.let {
            launchListApi.getPastLaunches()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(it)
        }
    }

    /**
     * Based on the count of the past launches decision is taken,
     * whether to get data from remote service or from local database.
     */
    private fun noOfPastLaunches(totalPastLaunches: Int) {
        if(totalPastLaunches == 0) {
            getPastLaunchesFromRemote()
        } else {
            this.totalPastLaunches = totalPastLaunches
            getPastLaunchesFromDB()
        }
    }

    /**
     * Handles database cache operation.
     */
    fun cacheInDatabase(launches: List<LaunchData>) {
        if (totalPastLaunches == 0) {
            // Cannot update the DB in main thread, so created a background thread
            val executor = Executors.newFixedThreadPool(1)
            val worker = Runnable { launchDao.insertPastLaunches(launches) }
            executor.execute(worker)
        }
    }
}