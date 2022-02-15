package com.hot.pocketdoctor.presentation.login


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hot.pocketdoctor.databinding.ActivityLoginBinding
import com.hot.pocketdoctor.presentation.MainActivity
import com.hot.pocketdoctor.presentation.login.findaccount.FindAccountActivity
import com.hot.pocketdoctor.presentation.login.findpassword.FindPasswordActivity
import com.hot.pocketdoctor.presentation.login.viewmodel.SignInViewModel
import com.hot.pocketdoctor.presentation.signup.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                // 로그인
                val email = binding.etLoginEmail.text.toString().trim()
                val password = binding.etLoginPassword.text.toString().trim()

                signInViewModel.email = email
                signInViewModel.password = password
                signInViewModel.postSignIn()
                observeSignIn()
            }

            btnSignup.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
                finish()
            }

            tvFindAccount.setOnClickListener {
                startActivity(Intent(this@LoginActivity, FindAccountActivity::class.java))
            }

            tvFindPassword.setOnClickListener {
                startActivity(Intent(this@LoginActivity, FindPasswordActivity::class.java))
            }
        }
    }

    private fun observeSignIn() {
        signInViewModel.isSignIn.observe(this, {
            if (it) {
                navigateToMain()
            } else {
                Toast.makeText(this, "Not Correct!", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}