package com.example.movieapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val url = "https://api.themoviedb.org/3/movie/"


    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        if(retrofit == null){

            retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()

        }
        return retrofit!!
    }
}