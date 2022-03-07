package com.hot.pocketdoctor.presentation.signup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.domain.repository.SignUpRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {

    private var _isSignUpSuccess = MutableLiveData<Boolean>()
    var isSignUpSuccess: LiveData<Boolean> = _isSignUpSuccess

    private var _verificationCode = MutableLiveData<String>()
    var verificationCode: LiveData<String> = _verificationCode

    private var _verifiedEmail = MutableLiveData<String>()
    var verifiedEmail: LiveData<String> = _verifiedEmail

    private var _email: String = ""
    var email: String = _email
        set(value) {
            _email = value
            field = value
        }

    private var _password: String = ""
    var password: String = _password
        set(value) {
            _password = value
            field = value
        }

    private var _name: String = ""
    var name: String = _name
        set(value) {
            _name = value
            field = value
        }

    private var _phoneNumber: String = ""
    var phoneNumber: String = _phoneNumber
        set(value) {
            _phoneNumber = value
            field = value
        }

    fun postSignUp() = viewModelScope.launch {
        runCatching { signUpRepository.postSignUpResult(ReqSignUpSuccessData(_email, _password, _name, _phoneNumber)) }
            .onSuccess {
                _isSignUpSuccess.postValue(true)
                Log.e(SIGNUP_SUCCESS_TAG, "${it.status}-${it.message}")
            }
            .onFailure {
                Log.e(SIGNUP_FAILED_TAG, "SignUp Failed")
                it.printStackTrace()
            }
    }

    fun postVerifyEmail(email: String) = viewModelScope.launch {
        runCatching { signUpRepository.postVerifyEmail(ReqVerifyEmailSuccessData(email)) }
            .onSuccess {
                Log.e(VERIFY_EMAIL_SUCCESS_TAG, "${it.verificationCode}")
                _verificationCode.postValue(it.verificationCode.toString())
                _verifiedEmail.postValue(it.verifiedEmail)
            }
            .onFailure {
                Log.e(VERIFY_EMAIL_FAILED_TAG, "Verify Failed")
            }
    }

    companion object {
        const val SIGNUP_SUCCESS_TAG = "postSignUp-Success"
        const val SIGNUP_FAILED_TAG = "postSignUp-Failed"
        const val VERIFY_EMAIL_SUCCESS_TAG = "postVerifyEmail-Success"
        const val VERIFY_EMAIL_FAILED_TAG = "postVerifyEmail-Failed"
    }
}