package com.example.movieapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.activities.TRDetailActivity
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.models.Movies

class MovieAdapter(val list: List<Movies.Result>) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageUrl = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
        val item = list.get(position)
        holder.binding.apply {
            var url = item.posterPath
            trTitle.setText(item.title)
            trDate.setText(item.releaseDate)

            if (url != null){
                var img = imageUrl+url
                Glide.with(root.context).load(img).into(trImg)
            }
            trCard.setOnClickListener {
                var intent = Intent(root.context, TRDetailActivity::class.java)
                intent.putExtra("title",item.title)
                intent.putExtra("overview",item.overview)
                intent.putExtra("date",item.releaseDate)
                intent.putExtra("image",imageUrl+url)
                intent.putExtra("avg",item.voteAverage)

                root.context.startActivity(intent)

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}