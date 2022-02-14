package com.hot.pocketdoctor.presentation.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentSignUpEmailNameBinding
import com.hot.pocketdoctor.presentation.signup.viewmodel.SignUpViewModel
import com.hot.pocketdoctor.util.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SignUpEmailNameFragment : Fragment() {

    private var _binding: FragmentSignUpEmailNameBinding? = null
    private val binding get() = _binding!!
    private val signUpViewModel: SignUpViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpEmailNameBinding.inflate(inflater, container, false)

        textWatcherEditText()
        setButtonClickListener()


        val view = binding.root
        return view
    }

    private fun textWatcherEditText() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                with(binding) {
                    if (s.toString().trim().isNotEmpty()) {
                        tvMessageCheckEmail.visibility = View.VISIBLE
                    } else {
                        tvMessageCheckEmail.visibility = View.INVISIBLE
                    }
                }

                with(binding.tvMessageCheckEmail) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString().trim()).matches()) {
                        // TODO : 중복 계정 확인?

                        text = "Cool!"
                        setTextColor(resources.getColor(R.color.primary_second_blue, context?.theme))
                    } else {
                        text = "Please Check Your Email Format"
                        setTextColor(resources.getColor(R.color.accent_red, context?.theme))
                    }
                }
            }
        })

        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                checkEmptyField()
            }
        })
    }


    private fun checkEmptyField() {
        if (binding.etEmail.text.isNullOrBlank() || binding.etName.text.isNullOrBlank()) {
            binding.btnNext.isEnabled = false
            binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
            binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
        } else {
            binding.btnNext.isEnabled = true
            binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.button_primary_round_blue)
            binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
        }
    }

    private fun setButtonClickListener() {
        binding.btnNext.setOnClickListener {
            signUpViewModel.email = binding.etEmail.text.toString().trim()
            signUpViewModel.name = binding.etName.text.toString().trim()
            navigate(R.id.action_signUpEmailNameFragment_to_signUpPasswordFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}