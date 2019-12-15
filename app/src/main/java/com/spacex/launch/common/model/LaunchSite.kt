package com.spacex.launch.common.model

import androidx.room.ColumnInfo
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Embedded data for inner user defined object value.
 * Used to store the launch location of the mission.
 *
 * @author Hari Hara Sudhan.N
 */
@Parcelize
data class LaunchSite (@ColumnInfo(name = "site_name")
                       @SerializedName("site_name")
                       var siteName: String? = null,
                       @ColumnInfo(name = "site_name_long")
                       @SerializedName("site_name_long")
                       var siteNameLong: String? = null): Parcelable