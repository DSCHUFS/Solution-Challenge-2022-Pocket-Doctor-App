package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hot.pocketdoctor.PocketDoctorApplication
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.adapter.DoctorListAdapter
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TreatmentListFragment :
    BaseFragment<FragmentTreatmentListBinding>(R.layout.fragment_treatment_list) {

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private val doctorListAdapter by lazy { DoctorListAdapter(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PocketDoctorSharedPreference.getUserToken() !== "") {
            initTreatmentListAdapter()
            fetchDoctorInfo()
            observeDoctorInfoData()
        } else {
            Toast.makeText(context, "Please Login First", Toast.LENGTH_SHORT).show()
        }
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