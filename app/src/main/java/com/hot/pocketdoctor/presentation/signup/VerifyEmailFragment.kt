package com.hot.pocketdoctor.presentation.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentVerifyEmailBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.signup.viewmodel.SignUpViewModel
import com.hot.pocketdoctor.util.navigateWithData
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VerifyEmailFragment :
    BaseFragment<FragmentVerifyEmailBinding>(R.layout.fragment_verify_email) {

    private val signUpViewModel: SignUpViewModel by sharedViewModel()

    private lateinit var verificationCode: String
    private lateinit var verifiedEmail: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeVerificationCode()
        observeVerifiedEmail()
        setButtonClickListener()
        setEditTextWatcher()
    }

    private fun observeVerificationCode() {
        signUpViewModel.verificationCode.observe(viewLifecycleOwner, {
            verificationCode = it
        })
    }

    private fun observeVerifiedEmail() {
        signUpViewModel.verifiedEmail.observe(viewLifecycleOwner, {
            verifiedEmail = it
        })
    }

    private fun setEditTextWatcher() {
        binding.etCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                checkVerificationCode()
            }
        })
    }

    private fun checkVerificationCode() {
        with(binding) {
            if (etCode.text.toString() == verificationCode) {

                tvCodeMessage.visibility = View.VISIBLE
                tvCodeMessage.text = resources.getString(R.string.verify_email_success)
                tvCodeMessage.setTextColor(
                    resources.getColor(
                        R.color.primary_second_blue,
                        context?.theme
                    )
                )

                btnVerify.isEnabled = true
                btnVerify.background =
                    ContextCompat.getDrawable(context!!, R.drawable.button_primary_round_blue)
                btnVerify.setTextColor(resources.getColor(R.color.background_white, context?.theme))
            } else {
                tvCodeMessage.visibility = View.VISIBLE
                tvCodeMessage.text = resources.getString(R.string.code_err_message)
                tvCodeMessage.setTextColor(resources.getColor(R.color.accent_red, context?.theme))

                btnVerify.isEnabled = false
                btnVerify.background =
                    ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                btnVerify.setTextColor(resources.getColor(R.color.background_white, context?.theme))
            }
        }
    }

    private fun setButtonClickListener() {
        with(binding) {
            btnVerify.setOnClickListener {
                navigateWithData(VerifyEmailFragmentDirections.actionVerifyEmailFragmentToSignUpEmailNameFragment(verifiedEmail, true))
            }

            tvCodeAgain.setOnClickListener {
                Toast.makeText(context, "CodeAgain", Toast.LENGTH_LONG).show()
            }
        }
    }
}