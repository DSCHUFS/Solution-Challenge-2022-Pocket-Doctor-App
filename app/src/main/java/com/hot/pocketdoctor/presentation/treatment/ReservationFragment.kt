package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.FragmentReservationBinding
import com.hot.pocketdoctor.presentation.base.BaseFragment
import com.hot.pocketdoctor.presentation.treatment.viewmodel.TreatmentViewModel
import com.hot.pocketdoctor.util.DateTimeUtils
import com.hot.pocketdoctor.util.navigate
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReservationFragment :
    BaseFragment<FragmentReservationBinding>(R.layout.fragment_reservation) {

    private val treatmentViewModel: TreatmentViewModel by sharedViewModel()

    private val args by navArgs<ReservationFragmentArgs>()

    private var currentSelectedDate: Long? = null

    private lateinit var calendarDateTime: String

    private lateinit var datePicker: MaterialDatePicker<*>
    private lateinit var timePicker: MaterialTimePicker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigatedArgs()
        setDatePickerButtonClickListener()
        setMakeReservationButtonClickListener()
    }

    private fun setNavigatedArgs() {
        treatmentViewModel.hospitalNo = args.hospitalNo
        treatmentViewModel.doctorNo = args.doctorNo
    }

    private fun setDatePickerButtonClickListener() {
        binding.ivDateTimePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val selectedDateInMillis = currentSelectedDate ?: System.currentTimeMillis()

        buildDatePicker(selectedDateInMillis)
        datePicker.show(childFragmentManager, "DatePicker")
    }

    private fun buildDatePicker(selectedDateInMillis: Long) {
        datePicker = MaterialDatePicker.Builder.datePicker()
            .setTheme(R.style.MaterialCalendarTheme)
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .setTitleText("Please select a date for reservation")
            .setSelection(selectedDateInMillis).build().apply {
                addOnPositiveButtonClickListener { dateInMillis -> onDateSelected(dateInMillis) }
                addOnNegativeButtonClickListener {
                    Toast.makeText(
                        context,
                        "You should select a date for reservation",
                        Toast.LENGTH_LONG
                    ).show()
                    calendarDateTime = ""
                }
            }
    }

    private fun onDateSelected(dateInMillis: Long) {
        currentSelectedDate = dateInMillis
        calendarDateTime =
            DateTimeUtils.convertMillisToDateTime(requireNotNull(currentSelectedDate))

        showTimePicker()
    }

    private fun showTimePicker() {
        buildTimePicker()
        timePicker.show(childFragmentManager, "TimePicker")
    }

    private fun buildTimePicker() {
        timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(9)
            .setMinute(0)
            .setTitleText("Please select a time for reservation")
            .setInputMode(INPUT_MODE_CLOCK)
            .build().apply {
                addOnPositiveButtonClickListener {
                    val time = " ${this.hour}:${this.minute}:00 "
                    calendarDateTime += time
                    Log.e("AAA", calendarDateTime)

                    setDateTimeTextView(calendarDateTime)
                    treatmentViewModel.startTime = calendarDateTime
                }
                addOnNegativeButtonClickListener {
                    Toast.makeText(
                        context,
                        "You should select a time for reservation",
                        Toast.LENGTH_LONG
                    ).show()
                    calendarDateTime = ""
                }
            }
    }

    private fun setDateTimeTextView(calendarDateTime: String) {
        with(binding) {
            if (calendarDateTime == "") {
                tvSelectedDateTime.visibility = View.INVISIBLE
            } else {
                tvSelectedDateTime.visibility = View.VISIBLE
                tvSelectedDateTime.text = calendarDateTime
            }
        }
    }

    private fun setMakeReservationButtonClickListener() {
        binding.btnReservation.setOnClickListener {
            postReservationInfo()
            navigateToMainByStatus()
        }
    }

    private fun postReservationInfo() {
        treatmentViewModel.postReservation()
    }

    private fun navigateToMainByStatus() {
        if (treatmentViewModel.isReservationCompleted.value == true) {
            Toast.makeText(context, "Reservation successfully saved.", Toast.LENGTH_LONG).show()
            navigate(R.id.action_reservationFragment_to_mainActivity)
        } else {
            Toast.makeText(context, "Error occurred! Please try again.", Toast.LENGTH_LONG).show()
        }
    }


}