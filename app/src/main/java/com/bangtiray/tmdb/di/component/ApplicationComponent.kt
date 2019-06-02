/*
 * *
 *  * Created by bangtiray on 5/25/19 2:26 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/25/19 2:26 PM
 *
 */

package com.bangtiray.tmdb.di.component

import com.bangtiray.tmdb.BaseApp
import com.bangtiray.tmdb.di.modul.ApplicationModule

import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun suntik(application: BaseApp)

}