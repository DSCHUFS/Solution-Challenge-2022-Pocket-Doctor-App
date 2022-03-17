package com.hot.pocketdoctor.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        if (PocketDoctorSharedPreference.getUserToken() !== "") {
            myPageViewModel.getUserInfo()
        }
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
        if (PocketDoctorSharedPreference.getUserToken() !== "") {
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

            rlLogoutContainer.setOnClickListener {
                setDialog()
            }
        }
    }

    private fun setDialog() {
        val view = View.inflate(context, R.layout.dialog_check_log_out, null)
        val dialogBuilder = AlertDialog.Builder(context!!)
            .setCancelable(false)
            .setView(view)

        val dialog = dialogBuilder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.findViewById<TextView>(R.id.btn_dialog_ok)?.setOnClickListener {
            dialog.dismiss()
            PocketDoctorSharedPreference.setUserToken("")
            Toast.makeText(context, "Logout Successful", Toast.LENGTH_SHORT).show()
        }

        dialog.findViewById<TextView>(R.id.btn_dialog_no)?.setOnClickListener {
            dialog.dismiss()
        }
    }
}