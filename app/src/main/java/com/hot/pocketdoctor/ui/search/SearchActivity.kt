package com.hot.pocketdoctor.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivitySearchBinding
import com.hot.pocketdoctor.databinding.ActivitySplashBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}