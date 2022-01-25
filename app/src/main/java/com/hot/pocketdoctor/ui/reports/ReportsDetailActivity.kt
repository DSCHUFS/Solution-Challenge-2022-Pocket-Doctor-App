package com.hot.pocketdoctor.ui.reports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.databinding.ActivityReportsDetailBinding
import com.hot.pocketdoctor.databinding.ItemReportsRecyclerBinding

class ReportsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}

