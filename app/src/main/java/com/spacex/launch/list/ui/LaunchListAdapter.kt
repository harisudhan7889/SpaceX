package com.spacex.launch.list.ui

import android.content.Context
import android.view.ViewGroup
import com.spacex.launch.R
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.common.recycler.BaseRvAdapter
import com.spacex.launch.common.recycler.OnItemClickListener

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchListAdapter(context: Context):
        BaseRvAdapter<LaunchData, OnItemClickListener<LaunchData>, LaunchViewHolder>(context) {

    /**
     * To be implemented in as specific adapter
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(inflate(R.layout.view_launch_parent, parent) as? LaunchView)
    }
}