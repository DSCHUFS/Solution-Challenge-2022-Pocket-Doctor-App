package com.hot.pocketdoctor.presentation.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentVerifyEmailBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.signup.viewmodel.SignUpViewModel
import com.hot.pocketdoctor.util.navigate
import com.hot.pocketdoctor.util.navigateWithData
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VerifyEmailFragment :
    BaseFragment<FragmentVerifyEmailBinding>(R.layout.fragment_verify_email) {

    private val args by navArgs<VerifyEmailFragmentArgs>()
    private val signUpViewModel: SignUpViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        with(binding) {
            btnVerify.setOnClickListener {
                Toast.makeText(context, "CodeAgain", Toast.LENGTH_LONG).show()
                navigateWithData(VerifyEmailFragmentDirections.actionVerifyEmailFragmentToSignUpEmailNameFragment(args.email))
            }

            tvCodeAgain.setOnClickListener {
                Toast.makeText(context, "CodeAgain", Toast.LENGTH_LONG).show()
            }
        }

    }

}