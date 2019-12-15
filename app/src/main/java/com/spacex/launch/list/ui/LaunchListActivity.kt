package com.spacex.launch.list.ui

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.spacex.launch.R
import com.spacex.launch.common.archcomponent.ViewModelFactory
import com.spacex.launch.common.model.*
import com.spacex.launch.common.recycler.CustomDividerDecorator
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.common.ui.BaseActivity
import com.spacex.launch.detail.ui.LaunchDetailActivity
import com.spacex.launch.list.archcomponent.LaunchListViewModel
import kotlinx.android.synthetic.main.activity_launch_list.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchListActivity : BaseActivity(), OnItemClickListener<LaunchData> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var launchListViewModel: LaunchListViewModel

    private var launchListAdapter: LaunchListAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_launch_list

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LaunchListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        addViewStateObserver()
    }

    override fun onStart() {
        super.onStart()
        launchListViewModel.invoke()
    }

    override fun onItemClicked(launchData: LaunchData) {
        startActivity(LaunchDetailActivity.getActivityIntent(this, launchData))
    }

    override fun initializeViewModel() {
        launchListViewModel = viewModelFactory.create(LaunchListViewModel::class.java)
    }

    private fun initViews() {
        initActionBarItems()
        launchListAdapter = LaunchListAdapter(this)
        launchListAdapter?.setItemClickListener(this)
        val divider = CustomDividerDecorator(this)
        divider.setMargins(resources.getDimensionPixelOffset(R.dimen.spacex_divider_margin_left), 0,
                resources.getDimensionPixelOffset(R.dimen.spacex_divider_margin_right), 0)
        launchList.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@LaunchListActivity)
            addItemDecoration(divider)
            adapter = launchListAdapter
        }
    }

    /**
     * Initializes the action bar items.
     */
    private fun initActionBarItems() {
        setScreenTitle(R.string.spacex_launch_list_title)
    }

    private fun addViewStateObserver() {
        launchListViewModel
                .getViewStateObserver()
                .observe(this, Observer<ViewState<LaunchData>> { viewState ->
                    viewState?.let {
                        when (it) {
                            is LoadingState -> {
                                showProgress()
                            }
                            is DataState -> {
                                if (it.data.isNotEmpty()) {
                                    @Suppress("UNCHECKED_CAST")
                                    launchListAdapter?.setItems(it.data as? ArrayList<LaunchData>)
                                }
                                hideProgress()
                            }
                            is ErrorState -> {
                                Timber.e(it.error)
                                hideProgress()
                            }
                        }
                    }
                })
    }
}
