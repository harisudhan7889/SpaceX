package com.spacex.launch.di

import android.app.Application
import com.spacex.launch.SpaceXApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Hari Hara Sudhan.N
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModuleBuilder::class,
    ViewModelModule::class])

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: SpaceXApplication)

}