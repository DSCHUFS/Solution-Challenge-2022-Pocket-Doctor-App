package com.hot.pocketdoctor.presentation.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentReportsBinding
import com.hot.pocketdoctor.databinding.FragmentSignUpPhoneNumberBinding
import com.hot.pocketdoctor.presentation.signup.viewmodel.SignUpViewModel
import com.hot.pocketdoctor.util.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpPhoneNumberFragment : Fragment() {

    private var _binding: FragmentSignUpPhoneNumberBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpPhoneNumberBinding.inflate(inflater,container,false)

        textWatcherEditText()
        setButtonClickListener()
        val view = binding.root
        return view
    }

    private fun textWatcherEditText() {
        binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                with(binding) {
                    if (s.toString().trim().isNotEmpty()) {
                        tvMessageCheckPhoneNumber.visibility = View.VISIBLE
                    } else {
                        tvMessageCheckPhoneNumber.visibility = View.INVISIBLE
                    }
                }

                with(binding.tvMessageCheckPhoneNumber) {
                    if (android.util.Patterns.PHONE.matcher(s.toString().trim()).matches()) {
                        text = "Cool!"
                        setTextColor(resources.getColor(R.color.primary_second_blue, context!!.theme))

                        binding.btnNext.isEnabled = true
                        binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.button_primary_round_blue)
                        binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                    } else {
                        text = "You have to follow the condition"
                        setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                        binding.btnNext.isEnabled = false
                        binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                        binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                    }
                }
            }
        })
    }

    private fun setButtonClickListener() {
        binding.btnNext.setOnClickListener {
            signUpViewModel.phoneNumber = binding.etPhoneNumber.text.toString().trim()
            signUpViewModel.postSignUp()
            navigate(R.id.action_signUpPhoneNumberFragment_to_mainActivity)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}