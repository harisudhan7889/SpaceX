package com.spacex.launch.detail.model

/**
 * Data class which holds basic details of the launch.
 *
 * @param missionName         name of the mission.
 * @param missionDescription  description about the mission.
 * @param thumbnailSource     thumbnail image remote url.
 * @param isSuccess           flag that denotes whether the launch is success.
 *
 * @author Hari Hara Sudhan.N
 */
data class BasicDetail(var missionName: String?,
                       var missionDescription: String?,
                       var thumbnailSource: String?,
                       var isSuccess: Boolean? = false): Detail()