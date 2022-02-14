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
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentSignUpPasswordBinding
import com.hot.pocketdoctor.presentation.signup.viewmodel.SignUpViewModel
import com.hot.pocketdoctor.util.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.regex.Pattern

class SignUpPasswordFragment : Fragment() {

    private var _binding: FragmentSignUpPasswordBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false)

        textWatcherEditText()
        setButtonClickListener()
        val view = binding.root
        return view
    }

    private fun textWatcherEditText() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            override fun afterTextChanged(s: Editable?) {
                with(binding) {
                    if (s.toString().trim().isNotEmpty()) {
                        tvMessageCheckPassword.visibility = View.VISIBLE
                    } else {
                        tvMessageCheckPassword.visibility = View.INVISIBLE
                    }

                    if (s.toString().trim().length >= 8) { // 8 자릿수 검사
                        if(Pattern.matches("^(?=.*[A-Za-z])(?=.*[\\d]).{8,}\$", s.toString().trim())) { // 영문자, 숫자 검사
                            if(Pattern.matches("^[A-Za-z\\d\$@\$!%*#?&]*\$", s.toString().trim())) { // 특수문자
                                with(tvMessageCheckPassword) {
                                    text = "Cool!"
                                    setTextColor(resources.getColor(R.color.primary_second_blue, context!!.theme))

                                }
                            } else {
                                with(tvMessageCheckPassword) {
                                    text = "You should include special symbols"
                                    setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                                    binding.btnNext.isEnabled = false
                                    binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                                    binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                                }
                            }
                        } else {
                            with(tvMessageCheckPassword) {
                                text = "You have to follow the condition"
                                setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                                binding.btnNext.isEnabled = false
                                binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                                binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                            }
                        }
                    } else {
                        with(tvMessageCheckPassword) {
                            text = "Please enter at least 8 characters"
                            setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                            binding.btnNext.isEnabled = false
                            binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                            binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                        }
                    }
                }
            }
        })

        binding.etPasswordAgain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                with(binding) {
                    if (s.toString().trim().isNotEmpty()) {
                        tvMessageCheckPasswordAgain.visibility = View.VISIBLE
                    } else {
                        tvMessageCheckPasswordAgain.visibility = View.INVISIBLE
                    }

                    if (s.toString().trim().length >= 8) { // 8 자릿수 검사
                        if(Pattern.matches("^(?=.*[A-Za-z])(?=.*[\\d]).{8,}\$", s.toString().trim())) { // 영문자, 숫자 검사
                            if(Pattern.matches("^[A-Za-z\\d\$@\$!%*#?&]*\$", s.toString().trim())) { // 특수문자
                                with(tvMessageCheckPasswordAgain) {
                                    if (s.toString().trim() == binding.etPassword.text.toString().trim()) {
                                        text = "Cool!"
                                        setTextColor(resources.getColor(R.color.primary_second_blue, context!!.theme))

                                        binding.btnNext.isEnabled = true
                                        binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.button_primary_round_blue)
                                        binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                                    } else {
                                        text = "Different password. Please check!"
                                        setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                                        binding.btnNext.isEnabled = false
                                        binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                                        binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                                    }
                                }
                            } else {
                                with(tvMessageCheckPasswordAgain) {
                                    text = "You should include special symbols"
                                    setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                                    binding.btnNext.isEnabled = false
                                    binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                                    binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                                }
                            }
                        } else {
                            with(tvMessageCheckPasswordAgain) {
                                text = "You have to follow the condition"
                                setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                                binding.btnNext.isEnabled = false
                                binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                                binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                            }
                        }
                    } else {
                        with(tvMessageCheckPasswordAgain) {
                            text = "Please enter at least 8 characters"
                            setTextColor(resources.getColor(R.color.accent_red, context!!.theme))

                            binding.btnNext.isEnabled = false
                            binding.btnNext.background = ContextCompat.getDrawable(context!!, R.drawable.rectangle_light_gray_radius_20)
                            binding.btnNext.setTextColor(resources.getColor(R.color.background_white, context!!.theme))
                        }
                    }
                }

            }
        })
    }

    private fun setButtonClickListener() {
        binding.btnNext.setOnClickListener {
            signUpViewModel.password = binding.etPasswordAgain.text.toString().trim()
            navigate(R.id.action_signUpPasswordFragment_to_signUpPhoneNumberFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}