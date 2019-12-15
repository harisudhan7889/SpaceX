package com.spacex.launch.detail.model

/**
 * Base class for the different launch details.
 *
 * @author Hari Hara Sudhan.N
 */
abstract class Detail {
    companion object {
        const val BASIC = 1
        const val MEDIA = 2
        const val OTHER = 3
    }
}