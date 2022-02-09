package com.hot.pocketdoctor.presentation.login.findpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityFindAccountBinding

class FindPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}