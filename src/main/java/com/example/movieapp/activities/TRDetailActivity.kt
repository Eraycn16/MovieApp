package com.example.movieapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.ActivityTrdetailBinding

class TRDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrdetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

          var title = intent.getStringExtra("title")
            var date =  intent.getStringExtra("date")
            var overview =  intent.getStringExtra("overview")
            var image =  intent.getStringExtra("image")
            var avg = intent.getFloatExtra("avg",0.0f)

            Log.d("TAG", "onCreate:"+avg)

            trDetail.setText("Özet: "+overview)
            trDetailDate.setText("Yayınlanma Tarihi: "+date)
            trDetailTitle.setText(title)

           var avrg = (avg*10)
            average.setText("%"+avrg)
             Glide.with(applicationContext).load(image).into(trDetailImg)

            circularProgressBar.apply {
                progress = avrg
                progressMax = 100f
               // setProgressWithAnimation(2f,1000)
                progressBarColor = Color.GREEN
            }
        }
    }
}