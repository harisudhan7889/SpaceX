package com.spacex.launch.common

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.spacex.launch.common.model.LaunchDao
import com.spacex.launch.common.model.LaunchData

/**
 * Class used to create Room Database.
 *
 * @author Hari Hara Sudhan.N
 */
@Database(entities = [(LaunchData::class)], version = 1)
abstract class SpaceXDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao

    companion object {
        private val DB_NAME = "SpaceXDatabase.db"

        @Volatile
        private var INSTANCE: SpaceXDatabase? = null

        fun getInstance(context: Context): SpaceXDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(
                                    context
                            ).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, SpaceXDatabase::class.java, DB_NAME).build()

    }

}