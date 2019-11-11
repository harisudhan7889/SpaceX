package com.spacex.launch.detail.ui

import android.content.Context
import android.view.ViewGroup
import com.spacex.launch.R
import com.spacex.launch.common.recycler.BaseRvAdapter
import com.spacex.launch.common.recycler.BaseViewHolder
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.detail.model.BasicDetail
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.MediaDetail
import com.spacex.launch.detail.model.OtherDetail

/**
 * Adapter to hold different detail types.
 *
 * @author Hari Hara Sudhan.N
 */
class LaunchDetailAdapter (context: Context):
        BaseRvAdapter<Detail, OnItemClickListener<Detail>,
        BaseViewHolder<Detail, OnItemClickListener<Detail>>>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Detail, OnItemClickListener<Detail>> {
        var viewHolder: BaseViewHolder<Detail, OnItemClickListener<Detail>>?
        when (viewType) {
            Detail.BASIC -> {
                viewHolder = BasicDetailViewHolder(inflate(R.layout.view_basic_detail, parent))
            }
            Detail.MEDIA -> {
                viewHolder = MediaDetailViewHolder(inflate(R.layout.view_media_detail, parent))
            }
            Detail.OTHER -> {
                viewHolder = OtherDetailViewHolder(inflate(R.layout.view_other_detail, parent))
            }
            else -> {
                viewHolder = super.createViewHolder(parent, viewType)
            }
        }
        return viewHolder
    }

    override fun getItemViewType(position: Int): Int {
        val detail = getItem(position)
        var type: Int?
        type = when(detail) {
            is BasicDetail -> Detail.BASIC
            is MediaDetail -> Detail.MEDIA
            is OtherDetail -> Detail.OTHER
            else -> Detail.BASIC
        }
        return type
    }

}