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
        return when (viewType) {
            Detail.BASIC -> {
                BasicDetailViewHolder(inflate(R.layout.view_basic_detail, parent))
            }
            Detail.MEDIA -> {
                MediaDetailViewHolder(inflate(R.layout.view_media_detail, parent))
            }
            Detail.OTHER -> {
                OtherDetailViewHolder(inflate(R.layout.view_other_detail, parent))
            }
            else -> {
                super.createViewHolder(parent, viewType)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is BasicDetail -> Detail.BASIC
            is MediaDetail -> Detail.MEDIA
            is OtherDetail -> Detail.OTHER
            else -> Detail.BASIC
        }
    }

}