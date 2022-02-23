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

        with(binding){
            userName.text = intent.getStringExtra("clickedReport_username")
            date.text = intent.getStringExtra("clickedReport_reportTime")
            symptom.text = intent.getStringExtra("clickedReport_symptom")
            hospitalName.text = intent.getStringExtra("clickedReport_hospital")
            doctorName.text = intent.getStringExtra("clickedReport_doctor")
        }
    }
}
