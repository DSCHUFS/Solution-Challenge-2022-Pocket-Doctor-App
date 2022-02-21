package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentHospitalDetailBinding

class HospitalDetailFragment : Fragment() {

    private var _binding: FragmentHospitalDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHospitalDetailBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

}