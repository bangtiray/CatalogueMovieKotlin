package com.bangtiray.tmdb.ui.fragment.profile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.bangtiray.tmdb.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.recycler, container, false)
        try {
            val message = arguments!!.getString(SEARCHKEY)
            if (message != null) {
                Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
            } else {

            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return view
    }
    companion object {
        val SEARCHKEY = "searchkey"
    }

}
