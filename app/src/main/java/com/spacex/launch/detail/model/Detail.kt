package com.spacex.launch.detail.model

import android.support.annotation.IntDef

/**
 * Base data class for the launch details.
 *
 * @param type  View type used to load different views in recyclerview.
 *
 * @author Hari Hara Sudhan.N
 */
abstract class Detail(var type: Int? = null) {
    companion object {
        @IntDef(BASIC, MEDIA, OTHER)
        @Retention(AnnotationRetention.SOURCE)
        annotation class DetailType

        const val BASIC = 1
        const val MEDIA = 2
        const val OTHER = 3
    }
}