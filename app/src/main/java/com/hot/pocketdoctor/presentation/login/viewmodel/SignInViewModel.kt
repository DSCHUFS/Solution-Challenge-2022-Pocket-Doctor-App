package com.hot.pocketdoctor.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference
import com.hot.pocketdoctor.domain.repository.SignInRepository
import kotlinx.coroutines.launch

class SignInViewModel(private val signInRepository: SignInRepository) : ViewModel() {

    private var _isSignIn = MutableLiveData<Boolean>()
    val isSignIn: LiveData<Boolean> = _isSignIn

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

    fun postSignIn() = viewModelScope.launch {
        runCatching { signInRepository.postSignInResult(ReqSignInSuccessData(_email, _password)) }
            .onSuccess { data ->
                Log.e(SIGN_IN_SUCCESS, "Token : ${data.token}")
                Log.e(SIGN_IN_SUCCESS, "${data.status} - ${data.message}")

                PocketDoctorSharedPreference.setUserToken(data.token)
                _isSignIn.postValue(true)
            }
            .onFailure {
                _isSignIn.postValue(false)
                it.printStackTrace()
            }
    }

    companion object {
        const val SIGN_IN_SUCCESS = "SignIn Success"
        const val SIGN_IN_FAILED = "SignIn Failed"
    }
}