package com.hot.pocketdoctor.ui.treatment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityTreatmentListBinding

class TreatmentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTreatmentListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreatmentListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}