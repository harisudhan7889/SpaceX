package com.spacex.launch.list.di

import com.spacex.launch.BuildConfig
import com.spacex.launch.common.di.ConvertorModule
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

    @LaunchListActivityScope
    @Provides
    fun launchListApi(retrofit: Retrofit): LauncheListApi {
       return retrofit.create(LauncheListApi::class.java)
    }

    @LaunchListActivityScope
    @Provides
    fun retrofit(gsonConverterFactory: GsonConverterFactory,
                 rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.SPACEX_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build()
    }

}