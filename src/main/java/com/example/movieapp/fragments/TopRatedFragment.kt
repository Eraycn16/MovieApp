package com.example.movieapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapters.TopRatedAdapter
import com.example.movieapp.databinding.FragmentTopRatedBinding
import com.example.movieapp.models.TopRated
import com.example.movieapp.services.Service
import com.example.movieapp.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TopRatedFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentTopRatedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedBinding.inflate(inflater,container,false)

        binding.trList.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val service =  ApiClient.getClient().create(Service::class.java)
        var topRated = service.getTopRated()

        var topRatedList: List<TopRated.Result>

        topRated.enqueue(object : Callback<TopRated>{
            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                if (response.isSuccessful){
                    topRatedList = response.body()!!.results as List<TopRated.Result>
                    getData(topRatedList)
                }
            }

            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("TAG", "onFailure: Failed")
            }

        })
    }

    fun getData(trList : List<TopRated.Result>){

        val adapter = TopRatedAdapter(trList)
        binding.trList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}