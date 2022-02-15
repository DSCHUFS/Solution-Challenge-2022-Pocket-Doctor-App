package com.hot.pocketdoctor.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityLogInHomeBinding

class LogInHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        binding.btnEmailLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}