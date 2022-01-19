package com.hot.pocketdoctor.ui.medication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMedicationRegisterBinding

class MedicationRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}