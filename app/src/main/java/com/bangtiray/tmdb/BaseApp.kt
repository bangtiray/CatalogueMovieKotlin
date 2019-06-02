package com.bangtiray.tmdb

import android.app.Application
import com.bangtiray.tmdb.di.component.ApplicationComponent
import com.bangtiray.tmdb.di.component.DaggerApplicationComponent
import com.bangtiray.tmdb.di.modul.ApplicationModule

open class BaseApp: Application(){
    lateinit var component: ApplicationComponent


    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {

        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.suntik(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {

        lateinit var instance: BaseApp private set

    }

}