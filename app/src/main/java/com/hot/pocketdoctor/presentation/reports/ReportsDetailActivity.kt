package com.hot.pocketdoctor.presentation.reports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityReportsDetailBinding

class ReportsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}

