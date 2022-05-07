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
import com.example.movieapp.databinding.FragmentUpcomingBinding
import com.example.movieapp.models.Movies
import com.example.movieapp.models.TopRated
import com.example.movieapp.services.Service
import com.example.movieapp.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpcomingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentUpcomingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpcomingBinding.inflate(inflater,container,false)
        binding.upcomingList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()


        val service =  ApiClient.getClient().create(Service::class.java)
        var upcoming = service.getUpcoming()

        var upList: List<Movies.Result>

        upcoming.enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful){
                    upList = response.body()!!.results as List<Movies.Result>
                    getData(upList)
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG", "onFailure:"+t.message.toString())
            }

        })
    }

    fun getData(list : List<Movies.Result>){

        val adapter = MovieAdapter(list)
        binding.upcomingList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}