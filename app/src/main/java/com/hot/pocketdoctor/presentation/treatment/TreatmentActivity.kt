package com.hot.pocketdoctor.presentation.treatment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityTreatmentBinding

class TreatmentActivity : AppCompatActivity() {

    private val binding: ActivityTreatmentBinding by lazy { ActivityTreatmentBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}