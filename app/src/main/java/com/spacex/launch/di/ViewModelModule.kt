package com.spacex.launch.di

import androidx.lifecycle.ViewModel
import com.spacex.launch.common.di.ViewModelKey
import com.spacex.launch.detail.archcomponent.LaunchDetailViewModel
import com.spacex.launch.list.archcomponent.LaunchListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Hari Hara Sudhan.N
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LaunchListViewModel::class)
    abstract fun bindLaunchListViewModel(viewModel: LaunchListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchDetailViewModel::class)
    abstract fun bindLauncDetailsViewModel(viewModel: LaunchDetailViewModel): ViewModel
}