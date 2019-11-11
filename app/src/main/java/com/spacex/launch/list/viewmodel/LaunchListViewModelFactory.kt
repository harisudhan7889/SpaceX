package com.spacex.launch.list.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.spacex.launch.list.repository.LaunchListRepository

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchListViewModelFactory(private val launchListRepository: LaunchListRepository):
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LaunchListViewModel::class.java)) {
            LaunchListViewModel(launchListRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}