package com.hot.pocketdoctor.presentation.treatment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hot.pocketdoctor.data.model.request.reservation.ReqReservationSuccessData
import com.hot.pocketdoctor.domain.model.treatment.DoctorInfoData
import com.hot.pocketdoctor.domain.model.treatment.HospitalDetailData
import com.hot.pocketdoctor.domain.repository.DoctorInfoRepository
import com.hot.pocketdoctor.domain.repository.HospitalInfoRepository
import com.hot.pocketdoctor.domain.repository.ReservationRepository
import kotlinx.coroutines.launch

class TreatmentViewModel(
    val doctorInfoRepository: DoctorInfoRepository,
    val hospitalInfoRepository: HospitalInfoRepository,
    val reservationRepository: ReservationRepository
) : ViewModel() {

    private var _hospitalDetailData = MutableLiveData<HospitalDetailData>()
    val hospitalDetailData: LiveData<HospitalDetailData> get() = _hospitalDetailData

    private var _doctorInfoData = MutableLiveData<List<DoctorInfoData.DoctorInfo>>()
    val doctorInfoData: LiveData<List<DoctorInfoData.DoctorInfo>> get() = _doctorInfoData

    private var _isReservationCompleted: Boolean = true
    var isReservationCompleted: Boolean = _isReservationCompleted
        set(value) {
            _isReservationCompleted = value
            field = value
        }

    private var _doctorNo: Int = 0
    var doctorNo: Int = _doctorNo
        set(value) {
            _doctorNo = value
            field = value
        }

    private var _hospitalNo: Int = 0
    var hospitalNo: Int = _hospitalNo
        set(value) {
            _hospitalNo = value
            field = value
        }

    private var _startTime: String = ""
    var startTime: String = _startTime
        set(value) {
            _startTime = value
            field = value
        }

    private var _description: String = ""
    var description: String = _description
        set(value) {
            _description = value
            field = value
        }

    private var _isVideo: Boolean = true
    var isVideo: Boolean = _isVideo
        set(value) {
            _isVideo = value
            field = value
        }

    fun getDoctorInfo() = viewModelScope.launch {
        runCatching { doctorInfoRepository.fetchDoctorInfo() }
            .onSuccess {
                _doctorInfoData.postValue(it.result)
                Log.e(DOCTOR_SUCCESS_TAG, "${it.result}")
            }
            .onFailure {
                it.printStackTrace()
                Log.e(DOCTOR_FAILED_TAG, "${it.message}")
            }
    }

    fun getHospitalDetail(doctorNo: Int) = viewModelScope.launch {
        runCatching { hospitalInfoRepository.fetchHospitalInfo(doctorNo) }
            .onSuccess {
                _hospitalDetailData.postValue(it)
                Log.e(HOSPITAL_SUCCESS_TAG, "$it")
            }
            .onFailure {
                it.printStackTrace()
                Log.e(HOSPITAL_FAILED_TAG, "${it.message}")
            }
    }

    fun postReservation() = viewModelScope.launch {
        runCatching {
            reservationRepository.postReservation(
                ReqReservationSuccessData(
                    _hospitalNo,
                    _doctorNo,
                    _startTime,
                    _description,
                    _isVideo
                )
            )
        }
            .onSuccess {
                _isReservationCompleted = true
                Log.e(RESERVATION_SUCCESS_TAG, "${it.status}-${it.message}")
            }
            .onFailure {
                _isReservationCompleted = false
                it.printStackTrace()
                Log.e(RESERVATION_FAILED_TAG, "${it.message}")
            }
    }

    companion object {
        const val HOSPITAL_SUCCESS_TAG = "HospitalInfo_Success"
        const val HOSPITAL_FAILED_TAG = "HospitalInfo_Failed"
        const val DOCTOR_SUCCESS_TAG = "QueryDoctorInfo_Success"
        const val DOCTOR_FAILED_TAG = "QueryDoctorInfo_Failed"
        const val RESERVATION_SUCCESS_TAG = "PostReservation_Success"
        const val RESERVATION_FAILED_TAG = "PostReservation_Failed"
    }
}