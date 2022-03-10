package com.hot.pocketdoctor.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hot.pocketdoctor.domain.repository.UserInfoRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {

    private var _userName = MutableLiveData<String>()
    var userName: LiveData<String> = _userName

    private var _userEmail: String = ""
    var userEmail: String = _userEmail
        set(value) {
            _userEmail = value
            field = value
        }

    private var _userPhoneNum: String = ""
    var userPhoneNum: String = _userPhoneNum
        set(value) {
            _userPhoneNum = value
            field = value
        }

    fun getUserInfo() = viewModelScope.launch {
        runCatching { userInfoRepository.getUserInfo() }
            .onSuccess {
                _userEmail = it.userEmail
                _userName.postValue(it.userName)
                _userPhoneNum = it.userPhoneNum
                Log.e(GET_USER_INFO_SUCCESS, _userName.value.toString())
            }
            .onFailure {
                it.printStackTrace()
                Log.e(GET_USER_INFO_FAILED, "failed")
            }
    }

    companion object {
        const val GET_USER_INFO_SUCCESS = "getUserInfo-Success"
        const val GET_USER_INFO_FAILED = "getUserInfo-Failed"
    }
}