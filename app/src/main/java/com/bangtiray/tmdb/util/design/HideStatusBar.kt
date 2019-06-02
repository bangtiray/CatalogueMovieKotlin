package com.bangtiray.tmdb.util.design

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

class HideStatusBar constructor(act: AppCompatActivity){

    private val ctx=act

    fun statusChange() {
        if (Build.VERSION.SDK_INT >= 21) {
            ctx.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        changeStatusBarColor()


    }
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = ctx.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }
}