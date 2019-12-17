package com.spacex.launch.list.ui

import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.common.recycler.BaseViewHolder
import com.spacex.launch.common.recycler.OnItemClickListener

/**
 * ViewHolder to display LaunchView.
 *
 * @see LaunchView
 *
 * @author Hari Hara Sudhan.N
 */
class LaunchViewHolder(private val launchView: LaunchView?)
    : BaseViewHolder<LaunchData, OnItemClickListener<LaunchData>>(launchView) {
    override fun onBind(launchData: LaunchData?, listener: OnItemClickListener<LaunchData>?) {
        launchView?.apply {
            bind(launchData)
            setOnClickListener { listener?.onItemClicked(launchData) }
        }
    }
}