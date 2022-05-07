package com.example.movieapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.activities.TRDetailActivity
import com.example.movieapp.databinding.HomeItemBinding
import com.example.movieapp.models.Categories
import com.example.movieapp.objects.AppObj

class HomeAdapter(val list: List<Categories.İtem>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: HomeItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HomeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list.get(position)

        holder.binding.apply {
            val url = item.posterPath

            if (url != null){
                var img = AppObj.urlImg+url
                Glide.with(root.context).load(img).into(itemImg)
            }

            homeCard.setOnClickListener {
                var intent = Intent(root.context, TRDetailActivity::class.java)
                intent.putExtra("title",item.title)
                intent.putExtra("overview",item.overview)
                intent.putExtra("date",item.releaseDate)
                intent.putExtra("image",AppObj.urlImg+url)
                intent.putExtra("avg",item.voteAverage)

                root.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}