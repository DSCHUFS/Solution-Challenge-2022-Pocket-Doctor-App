package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding

class ReservationFragment : Fragment() {

    private var _binding: FragmentTreatmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTreatmentListBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

}