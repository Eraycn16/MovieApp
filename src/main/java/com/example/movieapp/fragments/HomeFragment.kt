package com.example.movieapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapters.HomeAdapter
import com.example.movieapp.adapters.TopRatedAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.models.Categories
import com.example.movieapp.models.TopRated
import com.example.movieapp.services.Service
import com.example.movieapp.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.marvelMovieList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.dcMovieList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.allTopMovieList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val service =  ApiClient.getClient().create(Service::class.java)
        val listService = service.getMarvelMovie()
        val dcService = service.getDCMovie()
        val top = service.getAllTopMovie()

        var list: List<Categories.İtem>


        listService.enqueue(object : Callback<Categories>{
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful){
                    list = response.body()!!.items as List<Categories.İtem>
                    getData(list)
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.d("TAG", "onFailure:Failed")
            }

        })

        dcService.enqueue(object : Callback<Categories>{
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful){
                    list = response.body()!!.items as List<Categories.İtem>
                    getDataDc(list)
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.d("TAG", "onFailure:Failed")
            }

        })

        top.enqueue(object : Callback<Categories>{
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                if (response.isSuccessful){
                    list = response.body()!!.items as List<Categories.İtem>
                    getDataTop(list)
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.d("TAG", "onFailure:Failed")
            }

        })
    }

    fun getData(list: List<Categories.İtem>){

        val adapter = HomeAdapter(list)
        binding.marvelMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun getDataDc(list: List<Categories.İtem>){

        val adapter = HomeAdapter(list)
        binding.dcMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    fun getDataTop(list: List<Categories.İtem>){

        val adapter = HomeAdapter(list)
        binding.allTopMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}