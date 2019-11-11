package com.spacex.launch.list.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.spacex.launch.R
import com.spacex.launch.common.di.ContextModule
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.common.recycler.CustomDividerDecorator
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.common.ui.BaseActivity
import com.spacex.launch.detail.ui.LaunchDetailActivity
import com.spacex.launch.list.di.DaggerLaunchListComponent
import com.spacex.launch.list.viewmodel.LaunchListViewModel
import com.spacex.launch.list.viewmodel.LaunchListViewModelFactory
import kotlinx.android.synthetic.main.activity_launch_list.*
import javax.inject.Inject

/**
 * @author Hari Hara Sudhan.N
 */
class ListLaunchesActivity : BaseActivity(), OnItemClickListener<LaunchData> {

    @Inject
    lateinit var launchListViewModelFactory: LaunchListViewModelFactory

    private var launchListViewModel: LaunchListViewModel?=null
    private var launchListAdapter: LaunchListAdapter? = null

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ListLaunchesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_list)
        initViews()
        injectDependency()
        addLiveDataListener()
    }

    override fun onResume() {
        super.onResume()
        launchListViewModel?.getPastLaunchesFromRemote()
    }

    override fun onItemClicked(launchData: LaunchData) {
        startActivity(LaunchDetailActivity.getActivityIntent(this, launchData))
    }

    private fun initViews() {
        initActionBar()
        launchListAdapter = LaunchListAdapter(this)
        launchListAdapter?.setItemClickListener(this)
        val divider = CustomDividerDecorator(this)
        divider.setMargins(resources.getDimensionPixelOffset(R.dimen.spacex_divider_margin_left), 0,
                resources.getDimensionPixelOffset(R.dimen.spacex_divider_margin_right), 0)
        launchList.apply {
            layoutManager = LinearLayoutManager(this@ListLaunchesActivity)
            addItemDecoration(divider)
            adapter = launchListAdapter
        }
    }

    /**
     * Intializes the action bar.
     */
    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            title = resources.getString(R.string.spacex_launch_list_title)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
        }
    }

    private fun injectDependency() {
        val launchListComponent
                = DaggerLaunchListComponent.builder().contextModule(ContextModule(this)).build()
        launchListComponent.inject(this)
    }

    private fun addLiveDataListener() {
        launchListViewModel = ViewModelProvider(this, launchListViewModelFactory).get(LaunchListViewModel::class.java)
        launchListViewModel
                ?.getPastLaunchesFromDB()
                ?.observe(this, Observer<List<LaunchData>> { pastLaunches ->
            if(pastLaunches?.isNotEmpty() == true) {
                launchListAdapter?.setItems(pastLaunches as? ArrayList<LaunchData>)
            }
        })
    }
}
