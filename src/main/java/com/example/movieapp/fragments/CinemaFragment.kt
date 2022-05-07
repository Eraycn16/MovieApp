package com.example.movieapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.adapters.TopRatedAdapter
import com.example.movieapp.databinding.FragmentCinemaBinding
import com.example.movieapp.models.Movies
import com.example.movieapp.models.TopRated
import com.example.movieapp.services.Service
import com.example.movieapp.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CinemaFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentCinemaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCinemaBinding.inflate(inflater,container,false)
        binding.cineamList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val service =  ApiClient.getClient().create(Service::class.java)
        val moviesPlay = service.getPlayingMovie()

        var movies: List<Movies.Result>

        moviesPlay.enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    movies = response.body()!!.results as List<Movies.Result>
                    getData(movies)
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG", "onFailure: Failed")
            }
        })
    }
    fun getData(list : List<Movies.Result>){

        val adapter = MovieAdapter(list)
        binding.cineamList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}