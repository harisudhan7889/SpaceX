package com.spacex.launch.detail.ui

import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.view.View
import com.spacex.launch.R
import com.spacex.launch.common.AppUtil
import com.spacex.launch.common.recycler.BaseViewHolder
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.detail.model.BasicDetail
import com.spacex.launch.detail.model.Detail
import kotlinx.android.synthetic.main.view_basic_detail.view.*

/**
 * ViewHolder that holds views to display basic details.
 *
 * @see BasicDetail
 *
 * @author Hari Hara Sudhan.N
 */
class BasicDetailViewHolder(view: View?)
    : BaseViewHolder<Detail, OnItemClickListener<Detail>>(view) {

    override fun onBind(detail: Detail?, listener: OnItemClickListener<Detail>?) {
        val basicDetail = detail as? BasicDetail
        basicDetail?.let {
            val context = itemView.context
            @ColorInt val borderColor = if (it.isSuccess == true) {
                ContextCompat.getColor(context, android.R.color.holo_green_dark)
            } else {
                ContextCompat.getColor(context, android.R.color.holo_red_dark)
            }
            AppUtil.loadImageIntoCircularView(context, itemView.launchThumbnail, it.thumbnailSource,
                    borderColor, borderColor)
            itemView.missionName.text = it.missionName ?: "-"
            itemView.missionDescription.text = it.missionDescription
                    ?: context.resources.getString(R.string.spacex_no_detail_label)
        }
    }

}