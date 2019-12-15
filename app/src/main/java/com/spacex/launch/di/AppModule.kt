package com.spacex.launch.di

import com.spacex.launch.list.di.LaunchListModule
import dagger.Module

/**
 * @author Hari Hara Sudhan.N
 */
@Module(includes = [LaunchListModule::class])
abstract class AppModule
