package com.spacex.launch.list.di

import android.app.Application
import com.spacex.launch.BuildConfig
import com.spacex.launch.common.SpaceXDatabase
import com.spacex.launch.common.di.ConvertorModule
import com.spacex.launch.common.model.LaunchDao
import com.spacex.launch.list.endpoint.LauncheListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Hari Hara Sudhan.N
 */
@Module(includes = [ConvertorModule::class])
class LaunchListModule {

    @Provides
    fun launchListApi(@LaunchList retrofit: Retrofit): LauncheListApi {
       return retrofit.create(LauncheListApi::class.java)
    }

    @Provides
    @LaunchList
    fun retrofit(gsonConverterFactory: GsonConverterFactory,
                 rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SPACEX_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build()
    }

    @Provides
    fun dao(appContext: Application): LaunchDao {
        return SpaceXDatabase.getInstance(appContext).launchDao()
    }
}