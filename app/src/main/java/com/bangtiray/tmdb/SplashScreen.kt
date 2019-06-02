package com.bangtiray.tmdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bangtiray.tmdb.ui.MainActivity
import com.bangtiray.tmdb.util.design.HideStatusBar
import org.jetbrains.anko.startActivity

class SplashScreen : AppCompatActivity() {

    private val TIME_OUT = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val changeBar = HideStatusBar(this)
        changeBar.statusChange()
        Handler().postDelayed({
            startActivity<MainActivity>()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, TIME_OUT.toLong())
    }
}
