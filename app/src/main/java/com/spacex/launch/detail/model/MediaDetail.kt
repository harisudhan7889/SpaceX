package com.spacex.launch.detail.model

import android.support.annotation.IntDef

/**
 * Holds the media links.
 *
 * @param videoLink         youtube video url.
 * @param videoThumbnailUrl video thumbnail url.
 * @param articleLink       article link.
 * @param wikipediaLink     wikipedia link about the launch.
 *
 * @author Hari Hara Sudhan.N
 */
data class MediaDetail(var videoLink: String? = null,
                       var videoThumbnailUrl: String? = null,
                       var articleLink: String? = null,
                       var wikipediaLink: String? = null,
                       @MediaType var mediaType: Int? = null) : Detail() {
    companion object {
        @IntDef(DEFAULT, VIDEO, WIKI, ARTICLE)
        @Retention(AnnotationRetention.SOURCE)
        annotation class MediaType

        const val DEFAULT = 0
        const val VIDEO = 1
        const val WIKI = 2
        const val ARTICLE = 3
    }
}