package com.hot.pocketdoctor.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}