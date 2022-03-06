package com.hot.pocketdoctor.presentation.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentVerifyEmailBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.util.navigate

class VerifyEmailFragment :
    BaseFragment<FragmentVerifyEmailBinding>(R.layout.fragment_verify_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        with(binding) {
            btnVerify.setOnClickListener {
                Toast.makeText(context, "CodeAgain", Toast.LENGTH_LONG).show()
                navigate(R.id.action_verifyEmailFragment_to_signUpEmailNameFragment)
            }

            tvCodeAgain.setOnClickListener {
                Toast.makeText(context, "CodeAgain", Toast.LENGTH_LONG).show()
            }
        }

    }

}