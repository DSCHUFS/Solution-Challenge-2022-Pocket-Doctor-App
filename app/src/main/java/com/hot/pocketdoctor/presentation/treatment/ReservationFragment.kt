package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReservationFragment : Fragment() {

    private var _binding: FragmentTreatmentListBinding? = null
    private val binding get() = _binding!!

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTreatmentListBinding.inflate(inflater, container, false)

        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}