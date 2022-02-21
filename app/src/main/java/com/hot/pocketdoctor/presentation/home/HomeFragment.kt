package com.hot.pocketdoctor.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hot.pocketdoctor.databinding.FragmentHomeBinding
import com.hot.pocketdoctor.presentation.search.SearchActivity
import com.hot.pocketdoctor.presentation.treatment.TreatmentActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setButtonClickListener()
        return binding.root
    }

    private fun setButtonClickListener() {
        with(binding) {
            ivSearch.setOnClickListener {
                startActivity(Intent(context, SearchActivity::class.java))
            }
        }

        with(binding) {
            cvAnxiety.setOnClickListener { navigateToHospitalDetail() }
            cvDementia.setOnClickListener { navigateToHospitalDetail() }
            cvEatingDisorder.setOnClickListener { navigateToHospitalDetail() }
            cvInsomnia.setOnClickListener { navigateToHospitalDetail() }
            cvMelancholia.setOnClickListener { navigateToHospitalDetail() }
            cvOcd.setOnClickListener { navigateToHospitalDetail() }
            cvPanic.setOnClickListener { navigateToHospitalDetail() }
            cvEtc.setOnClickListener { navigateToHospitalDetail() }
        }
    }

    private fun navigateToHospitalDetail() {
        startActivity(Intent(context, TreatmentActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}