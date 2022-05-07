package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.fragments.*
import com.example.movieapp.models.TopRated
import com.example.movieapp.services.Service
import com.example.movieapp.utils.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupNav()
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        changeFragment(homeFragment)
        /*
        var service = ApiClient.getClient().create(Service::class.java)
        var topRated = service.getTopRated()


        topRated.enqueue(object : Callback<TopRated>{
            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                if (response.isSuccessful){
                    var list = response.body()!!.results
                    for(item in list!!){
                        var it = item!!.title
                        Log.d("TAG", "onResponse:"+it)
                    }
                }
            }

            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("TAG", "onFailure: Failed")
            }
        })
         */
    }

    fun setupNav(){
        actionBarToggle = ActionBarDrawerToggle(this,binding.drawerLay,binding.actBar.actionBar,0,0)
        binding.drawerLay.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId){

                R.id.home->{
                    //Toast.makeText(applicationContext, "HOME", Toast.LENGTH_SHORT).show()
                    val home = HomeFragment()
                    changeFragment(home)
                    binding.drawerLay.close()
                    true
                }
                R.id.toprated->{
                   // Toast.makeText(applicationContext, "TOP RATED", Toast.LENGTH_SHORT).show()
                    val topRatedFragment = TopRatedFragment()
                    changeFragment(topRatedFragment)
                    binding.drawerLay.close()
                    true
                }
                R.id.popular->{
                    val popularFragment = PopularFragment()
                    changeFragment(popularFragment)
                    binding.drawerLay.close()
                    true
                }
                R.id.cinema->{
                    val cinemaFragment = CinemaFragment()
                    changeFragment(cinemaFragment)
                    binding.drawerLay.close()
                    true
                }
                R.id.upcoming->{
                    val upcomingFragment = UpcomingFragment()
                    changeFragment(upcomingFragment)
                    binding.drawerLay.close()
                    true
                }
                else ->{
                    false
                }
            }
        }
    }

    fun changeFragment(fragment: Fragment){
        val tra = supportFragmentManager.beginTransaction()
        tra.replace(binding.frameLay.id,fragment)
        tra.commit()
    }
}