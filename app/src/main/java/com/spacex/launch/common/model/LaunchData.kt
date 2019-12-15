package com.spacex.launch.common.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Data object which is used as layer to store all the launch details in database.
 * This also contains embedded self defined objects.
 *
 * @see Links
 * @see RocketDetail
 * @see LaunchSite
 *
 * @author Hari Hara Sudhan.N
 */
@Parcelize
@Entity(tableName = "launchdata")
data class LaunchData(@PrimaryKey
                      @ColumnInfo(name = "flight_number")
                      @SerializedName("flight_number")
                      val flightNumber: Int,
                      @ColumnInfo(name = "mission_name")
                      @SerializedName("mission_name")
                      var missionName: String? = null,
                      @ColumnInfo(name = "launch_year")
                      @SerializedName("launch_year")
                      var launchYear: String? = null,
                      @ColumnInfo(name = "launch_date_utc")
                      @SerializedName("launch_date_utc")
                      var launchDateUtc: String? = null,
                      @ColumnInfo(name = "details")
                      var details: String? = null,
                      @ColumnInfo(name = "launch_success")
                      @SerializedName("launch_success")
                      var isSuccess: Boolean? = null,
                      @Embedded
                      var links: Links? = null,
                      @Embedded
                      var rocket: RocketDetail,
                      @Embedded
                      @SerializedName("launch_site")
                      var launchSite: LaunchSite? = null) : Parcelable