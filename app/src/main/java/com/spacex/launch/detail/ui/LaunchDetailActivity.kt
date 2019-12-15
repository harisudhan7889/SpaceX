package com.spacex.launch.detail.ui

import androidx.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.spacex.launch.R
import com.spacex.launch.common.AppUtil
import com.spacex.launch.common.archcomponent.ViewModelFactory
import com.spacex.launch.common.model.*
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.common.ui.BaseActivity
import com.spacex.launch.detail.archcomponent.LaunchDetailViewModel
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.MediaDetail
import kotlinx.android.synthetic.main.activity_launch_detail.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchDetailActivity: BaseActivity(), OnItemClickListener<Detail> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var launchDetailViewModel: LaunchDetailViewModel

    private var launchDetailAdapter: LaunchDetailAdapter? = null

    companion object {
        private const val LAUNCH_DETAILS = "LAUNCH_DETAILS"
        @JvmStatic
        fun getActivityIntent(context: Context, launchData: LaunchData): Intent {
            val intent = Intent(context, LaunchDetailActivity::class.java)
            intent.putExtra(LAUNCH_DETAILS, launchData)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        addViewStateObserver()
    }

    override fun onStart() {
        super.onStart()
        launchDetailViewModel.invoke(intent.getParcelableExtra(LAUNCH_DETAILS))
    }

    /**
     * Handles the click event.
     */
    override fun onItemClicked(detail: Detail?) {
        val mediaDetail = detail as? MediaDetail
        mediaDetail?.let {
            when(it.mediaType) {
                MediaDetail.VIDEO -> AppUtil.launchExternalApp(this, it.videoLink)
                MediaDetail.WIKI -> AppUtil.launchExternalApp(this, it.wikipediaLink)
                MediaDetail.ARTICLE -> AppUtil.launchExternalApp(this, it.articleLink)
            }
        }
    }

    /**
     * Layout that is to be placed into the base container.
     */
    override val layoutId: Int
        get() = R.layout.activity_launch_detail

    override fun initializeViewModel() {
        launchDetailViewModel = viewModelFactory.create(LaunchDetailViewModel::class.java)
    }

    /**
     * Method that initialize the views.
     */
    private fun initViews() {
        initActionBarItems()
        launchDetailAdapter = LaunchDetailAdapter(this)
        launchDetails.apply {
            layoutManager = LinearLayoutManager(this@LaunchDetailActivity)
            adapter = launchDetailAdapter
        }
    }

    /**
     * Initializes the action bar items.
     */
    private fun initActionBarItems() {
        setHomeButtonVisible(true)
        setScreenTitle(R.string.spacex_launch_detail_title)
    }

    /**
     * Method that add the Live Data listener.
     * Live Data is aware of its owner component's
     * lifecycle and listens for the result accordingly.
     * Result data are handled in the form state.
     *
     * @see LoadingState
     * @see DataState
     * @see ErrorState
     */
    private fun addViewStateObserver() {
        launchDetailViewModel.getViewStateObserver()
                .observe(this, Observer<ViewState<Detail>> {
                    when (it) {
                        is LoadingState -> {
                            showProgress()
                        }
                        is DataState -> {
                            if (it.data.isNotEmpty()) {
                                launchDetailAdapter?.let { adapter ->
                                    @Suppress("UNCHECKED_CAST")
                                    adapter.setItems(it.data as? ArrayList<Detail>)
                                    adapter.setItemClickListener(this)
                                }
                            }
                            hideProgress()
                        }
                        is ErrorState -> {
                            Timber.e(it.error)
                            hideProgress()
                        }
                    }
                })
    }
}