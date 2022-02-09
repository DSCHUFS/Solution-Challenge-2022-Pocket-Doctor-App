package com.hot.pocketdoctor.presentation.reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hot.pocketdoctor.databinding.ActivityReservationBinding

class ReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onRadioButtonClicked(view: View) {
//        if (view is RadioButton) {
//            val checked = view.isChecked
//
//            when (view.id) {
//
//            }
//        }
    }
}