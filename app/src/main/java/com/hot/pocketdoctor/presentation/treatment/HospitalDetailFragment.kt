package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentHospitalDetailBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import com.hot.pocketdoctor.util.DateTimeUtils
import com.hot.pocketdoctor.util.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HospitalDetailFragment : BaseFragment<FragmentHospitalDetailBinding>(R.layout.fragment_hospital_detail) {

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private val args by navArgs<HospitalDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchHospitalDetailData()
        observeHospitalDetailData()
        setReservationButtonClickListener()
    }

    private fun fetchHospitalDetailData() {
        treatmentViewModel.getHospitalDetail(args.doctorNo)
    }

    private fun observeHospitalDetailData() {
        treatmentViewModel.hospitalDetailData.observe(viewLifecycleOwner, { detailData ->
            val hospitalTime = DateTimeUtils.convertWeeklyToDailyTime(detailData.hospitalTime)
            val hospitalLocation = detailData.hospitalLocation
            val hospitalUrl = detailData.hospitalHomePage
            setHospitalDetailViews(hospitalTime, hospitalLocation, hospitalUrl)
        })
    }

    private fun setHospitalDetailViews(
        hospitalTime: Map<String, String>,
        hospitalLocation: String,
        hospitalUrl: String
    ) {
        with(binding) {
            tvDoctorName.text = args.doctorName
            tvHospitalName.text = args.hospitalName
            tvSubjects.text = args.subject
            tvLocation.text = hospitalLocation
            tvHomepage.text = hospitalUrl

            tvMonTime.text = hospitalTime["월"]
            tvTueTime.text = hospitalTime["화"]
            tvWedTime.text = hospitalTime["수"]
            tvThuTime.text = hospitalTime["목"]
            tvFriTime.text = hospitalTime["금"]
            tvSatTime.text = hospitalTime["토"]
            tvSunTime.text = hospitalTime["일"]
        }

    }

    private fun setReservationButtonClickListener() {
        binding.btnRequestReservation.setOnClickListener {
            navigate(R.id.action_hospitalDetailFragment_to_reservationFragment)
        }
    }

}