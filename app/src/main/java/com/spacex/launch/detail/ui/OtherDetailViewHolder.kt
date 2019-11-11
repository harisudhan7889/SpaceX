package com.spacex.launch.detail.ui

import android.view.View
import com.spacex.launch.common.recycler.BaseViewHolder
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.OtherDetail
import kotlinx.android.synthetic.main.view_other_detail.view.*

/**
 * Holder that holds view to display other details.
 *
 * @see OtherDetail
 *
 * @author Hari Hara Sudhan.N
 */
class OtherDetailViewHolder(view: View?)
    : BaseViewHolder<Detail, OnItemClickListener<Detail>>(view) {

    override fun onBind(detail: Detail?, listener: OnItemClickListener<Detail>?) {
        val otherDetail = detail as? OtherDetail
        otherDetail?.let {
            itemView.locationText.text = otherDetail.launchLocation
            itemView.rocketName.text = "${otherDetail.rocketName}, ${otherDetail.rocketType}"
        }
    }

}