package com.spacex.launch

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.spacex.launch.list.ui.ListLaunchesActivity

/**
 * @author Hari Hara Sudhan.N
 */
class SplashScreenActivity: AppCompatActivity() {

    private val WAIT_TIME = 5000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(ListLaunchesActivity.getIntent(this))
            finish()
        }, WAIT_TIME)
    }

}