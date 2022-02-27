package com.hot.pocketdoctor.presentation.treatment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData
import com.hot.pocketdoctor.domain.model.info.HospitalDetailData
import com.hot.pocketdoctor.domain.repository.DoctorInfoRepository
import com.hot.pocketdoctor.domain.repository.HospitalInfoRepository
import kotlinx.coroutines.launch

class TreatmentViewModel(
    val doctorInfoRepository: DoctorInfoRepository,
    val hospitalInfoRepository: HospitalInfoRepository
) : ViewModel() {

    private var _hospitalDetailData = MutableLiveData<HospitalDetailData>()
    val hospitalDetailData: LiveData<HospitalDetailData> get() = _hospitalDetailData

    private var _doctorInfoData = MutableLiveData<List<DoctorInfoData.DoctorInfo>>()
    val doctorInfoData: LiveData<List<DoctorInfoData.DoctorInfo>> get() = _doctorInfoData


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

    companion object {
        const val HOSPITAL_SUCCESS_TAG = "HospitalInfo_Success"
        const val HOSPITAL_FAILED_TAG = "HospitalInfo_Failed"
        const val DOCTOR_SUCCESS_TAG = "QueryDoctorInfo_Success"
        const val DOCTOR_FAILED_TAG = "QueryDoctorInfo_Failed"

    }
}