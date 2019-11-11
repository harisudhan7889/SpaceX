package com.spacex.launch.list.di

import android.content.Context
import com.spacex.launch.common.SpaceXDatabase
import com.spacex.launch.common.di.ContextModule
import com.spacex.launch.common.model.LaunchDao
import com.spacex.launch.list.endpoint.LauncheListApi
import com.spacex.launch.list.repository.LaunchListRepository
import com.spacex.launch.list.viewmodel.LaunchListViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * @author Hari Hara Sudhan.N
 */
@Module(includes = [LaunchListModule::class, ContextModule::class])
class LaunchesViewModelModule {

    @LaunchListActivityScope
    @Provides
    fun launcheListRepository(launcheListApi: LauncheListApi, launchDao: LaunchDao): LaunchListRepository {
        return LaunchListRepository(launcheListApi, launchDao)
    }

    @LaunchListActivityScope
    @Provides
    fun launchListDao(context: Context): LaunchDao {
        return SpaceXDatabase.getInstance(context).launchDao()
    }

    @LaunchListActivityScope
    @Provides
    fun launcheListViewModelFactory(launchListRepository: LaunchListRepository): LaunchListViewModelFactory {
        return LaunchListViewModelFactory(launchListRepository)
    }

}