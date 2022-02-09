package com.hot.pocketdoctor.presentation.medication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityMedicationInfoBinding

class MedicationInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}