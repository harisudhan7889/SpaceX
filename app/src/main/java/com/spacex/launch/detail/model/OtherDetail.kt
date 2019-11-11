package com.spacex.launch.detail.model

/**
 * Further other details are captured here.
 *
 * @param rocketName     name of the rocket used.
 * @param rocketType     rocket type.
 * @param launchLocation place where launch happened.
 *
 * @author Hari Hara Sudhan.N
 */
data class OtherDetail (var rocketName: String?,
                        var rocketType: String?,
                        var launchLocation: String?): Detail()