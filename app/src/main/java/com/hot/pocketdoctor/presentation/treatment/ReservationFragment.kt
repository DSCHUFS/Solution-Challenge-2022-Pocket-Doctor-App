package com.hot.pocketdoctor.presentation.treatment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
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

    private var currentSelectedDate: Long? = null

    private lateinit var calendarDateTime: String

    private lateinit var datePicker: MaterialDatePicker<*>
    private lateinit var timePicker: MaterialTimePicker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDatePickerButtonClickListener()
        setMakeReservationButtonClickListener()
        setEditTextWatcher()
        setRadioButtonClickListener()
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
                    val time = " ${this.hour}:${this.minute}:00"
                    calendarDateTime += time
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

    private fun setEditTextWatcher() {
        with(binding) {
            etProblem.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {

                    if (s.toString().length <= 200 && s.toString().isNotBlank()) {
                        tvProblemErrMsg.visibility = View.INVISIBLE
                        treatmentViewModel.description = s.toString()

                        setEnableReservationButton(true)
                    } else if (s.toString().isBlank()) {
                        tvProblemErrMsg.visibility = View.VISIBLE
                        tvProblemErrMsg.text = "You should write your problem!"

                        setEnableReservationButton(false)
                    } else if (s.toString().length > 200) {
                        tvProblemErrMsg.visibility = View.VISIBLE
                        tvProblemErrMsg.text = "Should less than 200 characters."

                        setEnableReservationButton(false)
                    }
                }
            })
        }
    }

    private fun setRadioButtonClickListener() {
        binding.rgContactType.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.rb_video -> treatmentViewModel.isVideo = true
                R.id.rb_voice -> treatmentViewModel.isVideo = false
            }
        }
    }

    private fun setEnableReservationButton(isEnabled: Boolean) {
        if (isEnabled) {
            binding.btnReservation.isEnabled = true
            binding.btnReservation.setBackgroundResource(R.drawable.button_primary_round_blue)
            setMakeReservationButtonClickListener()
        } else {
            binding.btnReservation.isEnabled = false
            binding.btnReservation.setBackgroundResource(R.drawable.rectangle_light_gray_radius_20)
        }
    }

    private fun setMakeReservationButtonClickListener() {
        binding.btnReservation.setOnClickListener {
            postReservationInfo()
            Toast.makeText(context, "Reservation Successfully saved", Toast.LENGTH_SHORT).show()
            navigateToMainByStatus()
        }
    }

    private fun postReservationInfo() {
        treatmentViewModel.postReservation()
    }

    private fun navigateToMainByStatus() {
        if (treatmentViewModel.isReservationCompleted) {
            Toast.makeText(context, "Reservation successfully saved.", Toast.LENGTH_LONG).show()
            navigate(R.id.action_reservationFragment_to_mainActivity)
        } else {
            Toast.makeText(context, "Error occurred! Please try again.", Toast.LENGTH_LONG).show()
        }
    }
}