package com.example.movielistapp


import com.example.movielistapp.Interface.delete_Movie
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielistapp.model.Movie
import com.example.movielistapp.model.MovieListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Fragment_First : Fragment(), delete_Movie {
    var getList_First: List<Movie>? = ArrayList()
    var getList_Second: List<Movie>? = ArrayList()
    var arrayList = ArrayList<Movie>()
    var arrayList2 = ArrayList<Movie>()
  //  var recyclerView: RecyclerView? = null
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerView2: RecyclerView
   // var : RecyclerView? = null
    var delete_movie: delete_Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__first, container, false)
        movieData
        movieData2
        delete_movie = this
        recyclerView = view.findViewById(R.id.fragment1_RV)
        recyclerView2 = view.findViewById(R.id.fragment1_RV2)
        val nestedScrollView: NestedScrollView = view.findViewById(R.id.nestedScroll)
        arrayList = ArrayList()
        for (i in getList_First!!.indices) {
            arrayList.add(getList_First!![i])
        }
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        val fragment1Adapter = Fragment1Adapter(arrayList, requireContext(), this)
        recyclerView.setAdapter(fragment1Adapter)
        arrayList2 = ArrayList()
        for (i in getList_Second!!.indices) {
            arrayList2.add(getList_Second!![i])
        }
        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v.getChildAt(0).bottom <= v.height + scrollY) {
                recyclerView2.setLayoutManager(LinearLayoutManager(context))
                val fragment1Adapter = Fragment1Adapter(arrayList2, requireContext(), this)
                recyclerView2.setAdapter(fragment1Adapter)
            }
        })
        return view
    }

    // handle error response
    private val movieData2: Unit
        private get() {
            RetrofitInstance.getInstance()?.apIinterface?.movieList2!!.enqueue(object :
                Callback<MovieListModel?> {
                override fun onResponse(
                    call: Call<MovieListModel?>,
                    response: Response<MovieListModel?>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()
                        getList_Second = movieList?.movies
                    } else {
                        // handle error response
                    }
                }

                override fun onFailure(call: Call<MovieListModel?>, t: Throwable) {}
            })
        }

    // handle error response
    private val movieData: Unit
        private get() {
            RetrofitInstance.getInstance()?.apIinterface?.movieList!!.enqueue(object :
                Callback<MovieListModel?> {
                override fun onResponse(
                    call: Call<MovieListModel?>,
                    response: Response<MovieListModel?>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()
                        getList_First = movieList?.movies
                    } else {
                        // handle error response
                    }
                }

                override fun onFailure(call: Call<MovieListModel?>, t: Throwable) {}
            })
        }

    override fun deleteMovie(imdbId: String?) {
        for (i in arrayList.indices) {
            if (arrayList[i].imdbId == imdbId) {
                arrayList.removeAt(i)
                val fragment1Adapter = Fragment1Adapter(
                    arrayList,
                    requireContext(),
                    object : delete_Movie {
                        override fun deleteMovie(imdbId: String?) {
                            deleteMovie(imdbId)
                        }
                    })
                recyclerView!!.adapter = fragment1Adapter
            }
        }
        for (i in arrayList2.indices) {
            if (arrayList2[i].imdbId == imdbId) {
                arrayList2.removeAt(i)
                val fragment1Adapter = Fragment1Adapter(
                    arrayList2,
                    requireContext(),
                    object : delete_Movie {
                        override fun deleteMovie(imdbId: String?) {
                            deleteMovie(imdbId)
                        }
                    })
                recyclerView2!!.adapter = fragment1Adapter
            }
        }
    }
}