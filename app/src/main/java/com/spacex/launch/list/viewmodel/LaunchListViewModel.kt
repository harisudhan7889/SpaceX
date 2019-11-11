package com.spacex.launch.list.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.list.repository.LaunchListRepository

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchListViewModel(private val launchListRepository: LaunchListRepository): ViewModel() {

    private var liveData: LiveData<List<LaunchData>>? = null

    fun getPastLaunchesFromDB(): LiveData<List<LaunchData>> {
        if (liveData == null) {
            liveData = launchListRepository.getPastLaunchesFromDB()
        }
        return liveData!!
    }

    fun getPastLaunchesFromRemote() {
        launchListRepository.getPastLaunchesFromRemote()
    }
}