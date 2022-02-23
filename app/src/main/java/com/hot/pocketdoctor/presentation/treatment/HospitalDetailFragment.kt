package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentHospitalDetailBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HospitalDetailFragment : BaseFragment<FragmentHospitalDetailBinding>(R.layout.fragment_hospital_detail) {

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private val args by navArgs<HospitalDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}