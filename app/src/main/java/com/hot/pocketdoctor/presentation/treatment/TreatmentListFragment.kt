package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.View
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding
import com.hot.pocketdoctor.databinding.ItemTreatmentListBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.adapter.TreatmentListAdapter
import com.hot.pocketdoctor.presentation.treatment.model.DoctorListData
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import com.hot.pocketdoctor.util.navigateWithData
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TreatmentListFragment :
    BaseFragment<FragmentTreatmentListBinding>(R.layout.fragment_treatment_list) {

    /**
     * 1. 병원 정보 로딩
     * 2. 로딩한 데이터 어댑터 리스트에 넣기
     * 3. 리사이클러뷰 세팅
     * 4. 리사이클러뷰 아이템 클릭 이벤트 (id에 맞게)
     */

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private lateinit var treatmentListAdapter: TreatmentListAdapter
    private lateinit var doctorList: MutableList<DoctorListData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTreatmentListAdapter()
        fetchDoctorInfo()
        setRecyclerView()
        observeDoctorInfoData()

    }

    private fun initTreatmentListAdapter() {
        treatmentListAdapter = TreatmentListAdapter()
        binding.rvTreatmentList.adapter = treatmentListAdapter
    }

    private fun fetchDoctorInfo() {
        treatmentViewModel.queryDoctorInfo()
    }

    private fun observeDoctorInfoData() {
        treatmentViewModel.doctorInfoData.observe(viewLifecycleOwner) { doctorInfoData ->
            doctorList.addAll(doctorInfoData.result.map {
                DoctorListData(
                    doctorNo = it.doctorNo,
                    doctorName = it.doctorName,
                    hospitalName = it.hospitalName,
                    subject = it.subject,
                    availability = it.availability
                )
            })
        }
    }


    private fun setRecyclerView() {
        treatmentListAdapter.doctorList = doctorList

        treatmentListAdapter.setItemClickListener(object :
            TreatmentListAdapter.OnItemClickListener {
            override fun onItemClick(view: ItemTreatmentListBinding, position: Int) {
                navigateWithData(
                    TreatmentListFragmentDirections.actionTreatmentListFragmentToHospitalDetailFragment(
                        // Data 전달
                    )
                )

            }
        })

    }

}