package com.example.movielistapp

import com.example.movielistapp.Interface.Fav_Update
import com.example.movielistapp.Database.SqliteData
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movielistapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielistapp.Fragment2Adapter
import com.example.movielistapp.model.Movie
import java.util.ArrayList

class Fragment_Second : Fragment(), Fav_Update {
    var getFavList = ArrayList<Movie>()
    var sqliteData: SqliteData? = null
    lateinit var recyclerView:RecyclerView
    //var recyclerView: RecyclerView? = null
   // var emptyFAV: TextView? = null
    lateinit var emptyFAV:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sqliteData = SqliteData(context)
        getFavList.clear()
        getFavList = sqliteData!!.favList
        val view = inflater.inflate(R.layout.fragment__second, container, false)
        emptyFAV = view.findViewById(R.id.emptyFavourite)
        recyclerView = view.findViewById(R.id.fragment2_RV)
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        if (!getFavList.isEmpty()) {
            val fragment2Adapter = Fragment2Adapter(getFavList, requireContext(), this)
            recyclerView.setAdapter(fragment2Adapter)
            emptyFAV.setVisibility(View.GONE)
            recyclerView.setVisibility(View.VISIBLE)
        } else {
            emptyFAV.setVisibility(View.VISIBLE)
            recyclerView.setVisibility(View.GONE)
        }
        return view
    }

    override fun fav_removed() {
        getFavList.clear()
        getFavList = sqliteData!!.favList
        if (!getFavList.isEmpty()) {
            val fragment2Adapter = Fragment2Adapter(getFavList, requireContext(), this)
            recyclerView!!.adapter = fragment2Adapter
            emptyFAV!!.visibility = View.GONE
            recyclerView!!.visibility = View.VISIBLE
        } else {
            emptyFAV!!.visibility = View.VISIBLE
            recyclerView!!.visibility = View.GONE
        }
    }
}