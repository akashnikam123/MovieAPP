package com.example.movielistapp

import com.example.movielistapp.APIinterface
import com.example.movielistapp.RetrofitInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance internal constructor() {
    var apIinterface: APIinterface

    companion object {
        var instance: RetrofitInstance? = null
        @JvmName("getInstance1")
        fun getInstance(): RetrofitInstance? {
            if (instance == null) {
                instance = RetrofitInstance()
            }
            return instance
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://task.auditflo.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apIinterface = retrofit.create(APIinterface::class.java)
    }
}