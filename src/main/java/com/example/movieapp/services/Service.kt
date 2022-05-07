package com.example.movieapp.services

import com.example.movieapp.models.Categories
import com.example.movieapp.models.Movies
import com.example.movieapp.models.TopRated
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("top_rated?api_key=3900d684f0914a301bc4b172300c0d5e")
    fun getTopRated():Call<TopRated>

    @GET("popular?api_key=3900d684f0914a301bc4b172300c0d5e")
    fun getPopular():Call<TopRated>

    @GET("now_playing?api_key=3900d684f0914a301bc4b172300c0d5e")
    fun getPlayingMovie():Call<Movies>

    @GET("upcoming?api_key=3900d684f0914a301bc4b172300c0d5e")
    fun getUpcoming(): Call<Movies>

    @GET("https://api.themoviedb.org/3/list/1?api_key=3900d684f0914a301bc4b172300c0d5e&language=tr-TR")
    fun getMarvelMovie():Call<Categories>

    @GET("https://api.themoviedb.org/3/list/3?api_key=3900d684f0914a301bc4b172300c0d5e&language=tr-TR")
    fun getDCMovie(): Call<Categories>

    @GET("https://api.themoviedb.org/3/list/10?api_key=3900d684f0914a301bc4b172300c0d5e&language=tr-TR")
    fun getAllTopMovie(): Call<Categories>
}