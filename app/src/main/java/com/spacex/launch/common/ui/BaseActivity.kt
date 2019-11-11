package com.spacex.launch.common.ui

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

/**
 * @author Hari Hara Sudhan.N
 */
open class BaseActivity: AppCompatActivity() {
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
}