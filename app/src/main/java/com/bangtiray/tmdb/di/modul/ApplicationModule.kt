/*
 * *
 *  * Created by bangtiray on 5/25/19 2:23 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/25/19 2:22 PM
 *
 */

package com.bangtiray.tmdb.di.modul

import android.app.Application
import com.bangtiray.tmdb.BaseApp
import com.it.asjastan.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}