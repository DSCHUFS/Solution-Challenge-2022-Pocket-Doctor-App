package com.hot.pocketdoctor.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hot.pocketdoctor.databinding.FragmentMyPageBinding
import com.hot.pocketdoctor.presentation.login.LogInHomeActivity

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)

        setButtonClickListener()
        return binding.root
    }

    private fun setButtonClickListener() {
        with(binding) {
            rlLoginContainer.setOnClickListener {
                startActivity(Intent(context, LogInHomeActivity::class.java))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}