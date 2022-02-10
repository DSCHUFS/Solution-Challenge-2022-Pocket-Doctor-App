package com.hot.pocketdoctor.presentation.medication

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMedicationRegisterBinding
import java.util.*

class MedicationRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val timeBtn = binding.medicationRegisterTimeBtn
        timeBtn.setOnClickListener {
            val cal = Calendar.getInstance()
            val startHour = cal.get(Calendar.HOUR_OF_DAY)
            val startMinute = cal.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                binding.registerTimeTextView1.setText("$hourOfDay:$minute")
            }, startHour, startMinute, true).show()

        }
    }
}
