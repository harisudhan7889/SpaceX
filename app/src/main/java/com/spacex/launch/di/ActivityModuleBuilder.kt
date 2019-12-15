package com.spacex.launch.di

import com.spacex.launch.detail.ui.LaunchDetailActivity
import com.spacex.launch.list.ui.LaunchListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module class that provides the Activities.
 *
 * @author Hari Hara Sudhan.N
 */
@Module
abstract class ActivityModuleBuilder {

    @ContributesAndroidInjector
    abstract fun provideLaunchListActivity(): LaunchListActivity

    @ContributesAndroidInjector
    abstract fun provideLaunchDetailActivity(): LaunchDetailActivity
}