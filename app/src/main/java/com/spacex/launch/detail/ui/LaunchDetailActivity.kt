package com.spacex.launch.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.spacex.launch.R
import com.spacex.launch.common.AppUtil
import com.spacex.launch.common.model.LaunchData
import com.spacex.launch.common.recycler.OnItemClickListener
import com.spacex.launch.common.ui.BaseActivity
import com.spacex.launch.detail.model.BasicDetail
import com.spacex.launch.detail.model.Detail
import com.spacex.launch.detail.model.MediaDetail
import com.spacex.launch.detail.model.OtherDetail
import kotlinx.android.synthetic.main.activity_launch_detail.*

/**
 * @author Hari Hara Sudhan.N
 */
class LaunchDetailActivity: BaseActivity(), OnItemClickListener<Detail> {

    private var launchDetailAdapter: LaunchDetailAdapter? = null
    private var launcData: LaunchData? = null
    private val details = ArrayList<Detail>()

    companion object {
        const val KEY = "LAUNCH_DETAIL"
        @JvmStatic
        fun getActivityIntent(context: Context, launchData: LaunchData): Intent {
            val intent = Intent(context, LaunchDetailActivity::class.java)
            intent.putExtra(KEY, launchData)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_detail)
        initViews()
        updateAdapter()
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
     * Method that initialize the recyclerview.
     */
    private fun initViews() {
        initActionBar()
        launchDetailAdapter = LaunchDetailAdapter(this)
        launchDetails.apply {
            layoutManager = LinearLayoutManager(this@LaunchDetailActivity)
            adapter = launchDetailAdapter
        }
    }

    /**
     * Intializes the action bar.
     */
    private fun initActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            title = resources.getString(R.string.spacex_launch_detail_title)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Update the adapter with different details and also set the
     * listener for the item click.
     *
     * @see BasicDetail
     * @see MediaDetail
     * @see OtherDetail
     */
    private fun updateAdapter() {
        launcData = intent.getParcelableExtra(KEY)
        launcData?.also {
            details add (it.basic())
            details add (it.media())
            details add (it.other())
            launchDetailAdapter?.let {
                it.setItems(details)
                it.setItemClickListener(this)
            }
        }
    }

    // Kotlin's Extension function starts

    /**
     * Constructs the basic detail from the response.
     *
     * @return basic detail.
     */
    private fun LaunchData.basic(): Detail? {
        return BasicDetail(missionName, details, links?.missionPatch, isSuccess)
    }

    /**
     * Constucts the media detail from the response.
     *
     * @return media details which contains links.
     */
    private fun LaunchData.media(): Detail? {
        return links?.let {
            var videoThumbnail: String? = null
            if(!it.youtubeId.isNullOrBlank()) {
                videoThumbnail = "https://img.youtube.com/vi/${it.youtubeId}/1.jpg"
            }
            MediaDetail(it.videoLink, videoThumbnail, it.articleLink, it.wikiLink)
        }
    }

    /**
     * Constructs other details from the respose.
     *
     * @return other details.
     */
    private fun LaunchData.other(): Detail? {
        val launchLocation = launchSite?.siteNameLong
        val (rocketName, rocketType) = rocket
        return OtherDetail(rocketName, rocketType, launchLocation)
    }

    // Extension function ends

    /**
     * infix function to add detail to the array.
     * This is used to check null safety at one place
     * before adding the detail to the array.
     *
     * @param detail can be any type of detail scoped to generic.
     */
    private infix fun ArrayList<Detail>.add(detail: Detail?) {
        detail?.let {
            add(detail)
        }
    }
}