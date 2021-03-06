package com.hot.pocketdoctor.presentation.login.findaccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityFindAccountBinding

class FindAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.findAccountVerifyButton.setOnClickListener {
            binding.findAccountVerifyButton.isSelected = binding.findAccountVerifyButton.isSelected != true
        }
    }
}