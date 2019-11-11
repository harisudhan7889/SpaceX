package com.spacex.launch.list.ui

import android.content.Context
import android.support.annotation.ColorInt
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import com.spacex.launch.R
import com.spacex.launch.common.AppUtil
import com.spacex.launch.common.model.LaunchData
import kotlinx.android.synthetic.main.view_launch.view.*

/**
 * Custom view to display the launch details.
 * Specifically created for the launch list recyclerview.
 *
 * @author Hari Hara Sudhan.N
 */
class LaunchView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_launch, this, true)
    }

    fun bind(launchData: LaunchData?) {
        launchData?.let {
            missionName.text = it.missionName
            launchYear.text = it.launchYear
            @ColorInt val borderColor = if (it.isSuccess == true) {
                ContextCompat.getColor(context, android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(context, android.R.color.holo_red_dark)
            }
            AppUtil.loadImageIntoCircularView(context, launchThumbnail,
                    it.links?.missionPatch, borderColor, borderColor)
            launchDetails.text = it.details
        }
    }

}