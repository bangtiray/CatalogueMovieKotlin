/*
 * *
 *  * Created by bangtiray on 5/25/19 2:20 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/25/19 2:20 PM
 *
 */

package  com.bangtiray.tmdb.di.component

import com.bangtiray.tmdb.di.modul.ActivityModule
import com.bangtiray.tmdb.ui.MainActivity

import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))

interface ActivityComponent{
    fun inject(mainActivity: MainActivity)
}