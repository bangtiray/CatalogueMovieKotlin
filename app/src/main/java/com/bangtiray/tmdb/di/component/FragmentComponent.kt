/*
 * *
 *  * Created by bangtiray on 5/25/19 2:26 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/25/19 2:26 PM
 *
 */

package com.bangtiray.tmdb.di.component


import com.bangtiray.tmdb.di.modul.FragmentModule
import com.bangtiray.tmdb.ui.fragment.home.HomeFragment
import com.bangtiray.tmdb.ui.fragment.playnow.PlaynowFragment
import com.bangtiray.tmdb.ui.fragment.search.SearchFragment
import com.bangtiray.tmdb.ui.fragment.upcoming.UpcomingFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(playnowFragment: PlaynowFragment)
    fun inject(upcomingFragment: UpcomingFragment)
    fun inject(searchFragment: SearchFragment)
}