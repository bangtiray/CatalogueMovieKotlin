/*
 * *
 *  * Created by bangtiray on 5/25/19 2:23 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/25/19 2:22 PM
 *
 */

package  com.bangtiray.tmdb.di.modul


import com.bangtiray.tmdb.ui.fragment.conpre.Contract
import com.bangtiray.tmdb.ui.fragment.conpre.Presenter
import com.bangtiray.tmdb.ui.fragment.home.HomeContract
import com.bangtiray.tmdb.ui.fragment.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module

class FragmentModule {
    @Provides
    fun provideHomeFragPresenter(): HomeContract.Presenter{
        return HomePresenter()
    }

    @Provides
    fun globalPresebter():Contract.Presenter{
        return Presenter()
    }
}