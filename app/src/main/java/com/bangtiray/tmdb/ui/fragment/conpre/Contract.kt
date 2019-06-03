package com.bangtiray.tmdb.ui.fragment.conpre

import com.bangtiray.tmdb.BaseContract
import com.bangtiray.tmdb.models.ResultsItem

class Contract {
    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun loadData(totalPage: Int, list: List<ResultsItem>?)
        fun showError(localizedMessage: String)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData(cat:String, page:Int)
        fun searchData(search: String, page:Int)

    }
}