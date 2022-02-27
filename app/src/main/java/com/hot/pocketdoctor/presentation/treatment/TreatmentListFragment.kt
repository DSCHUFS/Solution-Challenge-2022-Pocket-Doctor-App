package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.adapter.DoctorListAdapter
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TreatmentListFragment :
    BaseFragment<FragmentTreatmentListBinding>(R.layout.fragment_treatment_list) {

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private val doctorListAdapter by lazy { DoctorListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTreatmentListAdapter()
        fetchDoctorInfo()
        observeDoctorInfoData()

    }

    private fun initTreatmentListAdapter() {
        binding.rvTreatmentList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTreatmentList.adapter = doctorListAdapter
    }

    private fun fetchDoctorInfo() {
        treatmentViewModel.getDoctorInfo()
    }

    private fun observeDoctorInfoData() {
        treatmentViewModel.doctorInfoData.observe(viewLifecycleOwner) {
            doctorListAdapter.submitList(it)
        }
    }
}