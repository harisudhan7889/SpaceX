package com.spacex.launch.common.model

import androidx.room.ColumnInfo
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Embedded data for inner user defined object value.
 * Used to store the rocket details.
 *
 * @author Hari Hara Sudhan.N
 */
@Parcelize
data class RocketDetail(@ColumnInfo(name = "rocket_name")
                        @SerializedName("rocket_name")
                        var rocketName: String? = null,
                        @ColumnInfo(name = "rocket_type")
                        @SerializedName("rocket_type")
                        var rocketType: String? = null): Parcelable