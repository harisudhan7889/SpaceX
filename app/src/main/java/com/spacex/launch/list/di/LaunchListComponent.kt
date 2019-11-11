package com.spacex.launch.list.di

import com.spacex.launch.list.ui.ListLaunchesActivity
import dagger.Component

/**
 * @author Hari Hara Sudhan.N
 */
@LaunchListActivityScope
@Component(modules = [LaunchesViewModelModule::class])
interface LaunchListComponent {
    fun inject(listLaunchesActivity: ListLaunchesActivity)
}