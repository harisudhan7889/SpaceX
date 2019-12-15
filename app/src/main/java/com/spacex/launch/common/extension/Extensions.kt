package com.spacex.launch.common.extension

import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.detail.model.BasicDetail
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.MediaDetail
import com.spacex.launch.detail.model.OtherDetail

/**
 * @author Hari Hara Sudhan.N
 */

/**
 * Constructs the basic detail from the response.
 *
 * @return basic detail.
 */
fun LaunchData.basic(): Detail? {
    return BasicDetail(missionName, details, links?.missionPatch, isSuccess)
}

/**
 * Constructs the media detail from the response.
 *
 * @return media details which contains links.
 */
fun LaunchData.media(): Detail? {
    return links?.let {
        var videoThumbnail: String? = null
        if(!it.youtubeId.isNullOrBlank()) {
            videoThumbnail = "https://img.youtube.com/vi/${it.youtubeId}/1.jpg"
        }
        MediaDetail(it.videoLink, videoThumbnail, it.articleLink, it.wikiLink)
    }
}

/**
 * Constructs other details from the respose.
 *
 * @return other details.
 */
fun LaunchData.other(): Detail? {
    val launchLocation = launchSite?.siteNameLong
    val (rocketName, rocketType) = rocket
    return OtherDetail(rocketName, rocketType, launchLocation)
}