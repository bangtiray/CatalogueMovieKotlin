/*
 * *
 *  * Created by bangtiray on 5/25/19 2:22 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/25/19 2:22 PM
 *
 */


package com.bangtiray.tmdb.di.modul

import android.support.v7.app.AppCompatActivity

import dagger.Module
import dagger.Provides


@Module

class ActivityModule(private var activity: AppCompatActivity) {
    @Provides
    fun provideActivity(): AppCompatActivity {
        return activity
    }
}
