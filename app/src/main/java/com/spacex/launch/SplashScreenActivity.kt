package com.spacex.launch

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.spacex.launch.list.ui.LaunchListActivity

/**
 * @author Hari Hara Sudhan.N
 */
class SplashScreenActivity: AppCompatActivity() {

    companion object {
        private const val WAIT_TIME = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(LaunchListActivity.getIntent(this))
            finish()
        }, WAIT_TIME)
    }

}