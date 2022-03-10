package com.hot.pocketdoctor.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference
import com.hot.pocketdoctor.databinding.FragmentMyPageBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.login.LogInHomeActivity
import com.hot.pocketdoctor.presentation.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel: MainViewModel by sharedViewModel()

    private var userName: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPageViewModel.getUserInfo()
        observeUserName()
        setButtonClickListener()
    }

    private fun observeUserName() {
        myPageViewModel.userName.observe(viewLifecycleOwner, {
            userName = it
            checkUserToken()
        })
    }

    private fun checkUserToken() {
        Log.e("AS", "${PocketDoctorSharedPreference.getUserToken()}")
        if (PocketDoctorSharedPreference.getUserToken() !== "") {
            Log.e("asdfasdf", "Asdfasdf")
            binding.tvAuthStatus.text = userName
        } else {
            binding.tvAuthStatus.text = resources.getString(R.string.my_page_user)
        }
    }

    private fun setButtonClickListener() {
        with(binding) {
            rlLoginContainer.setOnClickListener {
                startActivity(Intent(context, LogInHomeActivity::class.java))
            }
        }
    }


}