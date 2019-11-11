package com.spacex.launch.common.model

import android.arch.persistence.room.ColumnInfo
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Embedded data for inner user defined object value.
 * Used to store the document links.
 *
 * @author Hari Hara Sudhan.N
 */
@Parcelize
data class Links(@ColumnInfo(name = "video_link")
                 @SerializedName("video_link")
                 var videoLink: String? = null,
                 @ColumnInfo(name = "mission_patch")
                 @SerializedName("mission_patch")
                 var missionPatch: String? = null,
                 @ColumnInfo(name = "wiki_link")
                 @SerializedName("wikipedia")
                 var wikiLink: String?,
                 @ColumnInfo(name = "article_link")
                 @SerializedName("article_link")
                 var articleLink: String?,
                 @ColumnInfo(name = "youtube_id")
                 @SerializedName("youtube_id")
                 var youtubeId: String?) : Parcelable