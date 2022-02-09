package com.hot.pocketdoctor.presentation.hospitaldetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityHospitalDetailBinding

class HospitalDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHospitalDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}