package com.spacex.launch.common.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

/**
 * Includes methods that offer abstract
 * access to the app's database.
 *
 * @author Hari Hara Sudhan.N
 */
@Dao
interface LaunchDao {

    /**
     * Retrieves all the past launches available in database.
     */
    @Query("Select * From launchdata")
    fun getPastLaunches(): Single<List<LaunchData>>

    /**
     * Inserts the past launches to the database.
     * OnConflict strategy constant to replace the old data and continue the transaction.
     *
     * @see OnConflictStrategy.REPLACE
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPastLaunches(launches: List<LaunchData>?)

    /**
     * Delete the data from database.
     */
    @Query("DELETE FROM launchdata")
    fun deleteAllPastLaunches()

    /**
     * Gets the count of database entry for the launches.
     */
    @Query("SELECT COUNT(flight_number) FROM launchdata")
    fun getPastLaunchCount(): Single<Int>
}