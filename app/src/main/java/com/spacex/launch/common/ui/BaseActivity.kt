package com.spacex.launch.common.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.spacex.launch.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_base.view.*

/**
 * @author Hari Hara Sudhan.N
 */
abstract class BaseActivity: AppCompatActivity() {

    protected abstract val layoutId: Int

    protected abstract fun initializeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeToolbar()
        initializeViewModel()
    }

    override fun setContentView(layoutResID: Int) {
        val baseLayout = layoutInflater.inflate(R.layout.activity_base, null)
        layoutInflater.inflate(layoutResID, baseLayout.layoutContainer, true)
        super.setContentView(baseLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var isEventHandled = false
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                isEventHandled = true
            }
        }
        return isEventHandled || super.onOptionsItemSelected(item)
    }

    private fun initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.title = ""
        }
    }

    fun setHomeButtonVisible(isButtonVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isButtonVisible)
    }

    fun setScreenTitle(@StringRes resId: Int) {
        setScreenTitle(resources.getString(resId))
    }

    fun setScreenTitle(title: String) {
        supportActionBar?.let {
            it.title = title
        }
    }

    fun showProgress() {
        if (loadingIndicator.visibility == View.GONE) {
            loadingIndicator.visibility = View.VISIBLE
        }
    }

    fun hideProgress() {
        if (loadingIndicator.visibility == View.VISIBLE) {
            loadingIndicator.visibility = View.GONE
        }
    }
}