package com.hot.pocketdoctor.presentation.mypage.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hot.pocketdoctor.databinding.ActivityNotificationSettingsBinding

class NotificationSettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}