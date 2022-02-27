package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentReservationBinding
import com.hot.pocketdoctor.databinding.FragmentTreatmentListBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import com.hot.pocketdoctor.util.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReservationFragment : BaseFragment<FragmentReservationBinding>(R.layout.fragment_reservation) {

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private val args by navArgs<ReservationFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMakeReservationButtonClickListener()
    }

    private fun setMakeReservationButtonClickListener() {
        navigate(R.id.action_reservationFragment_to_mainActivity)
    }


}