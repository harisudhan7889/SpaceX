package com.spacex.launch.common.di

import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module that provides the GsonConvertor and RxJava2Adapter factory
 * methods used by retrofit client.
 *
 * @author Hari Hara Sudhan.N
 */
@Module
class ConvertorModule {

    @Provides
    fun gsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun rxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}