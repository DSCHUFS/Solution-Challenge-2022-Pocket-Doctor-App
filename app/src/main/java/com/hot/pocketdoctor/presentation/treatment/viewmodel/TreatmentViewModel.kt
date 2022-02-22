package com.hot.pocketdoctor.presentation.treatment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData
import com.hot.pocketdoctor.domain.model.info.HospitalDetailData
import com.hot.pocketdoctor.domain.repository.DoctorInfoRepository
import kotlinx.coroutines.launch

class TreatmentViewModel(val doctorInfoRepository: DoctorInfoRepository) : ViewModel() {

    private var _hospitalDetailData = MutableLiveData<HospitalDetailData>()
    val hospitalDetailData: LiveData<HospitalDetailData> get() = _hospitalDetailData

    private var _doctorInfoData = MutableLiveData<DoctorInfoData>()
    val doctorInfoData: LiveData<DoctorInfoData> get() = _doctorInfoData

    fun queryDoctorInfo() = viewModelScope.launch {
        runCatching { doctorInfoRepository.fetchDoctorInfo() }
            .onSuccess {
                _doctorInfoData.postValue(it)
                Log.e(DOCTOR_SUCCESS_TAG, "$it")
            }
            .onFailure {
                it.printStackTrace()
                Log.e(DOCTOR_FAILED_TAG, "${it.message}")
            }
    }

//    fun queryHospitalDetail() = viewModelScope.launch {
//        runCatching { }
//            .onSuccess {
//                _hospitalDetailData.postValue()
//            }
//            .onFailure {
//                it.printStackTrace()
//            }
//    }

    companion object {
        const val HOSPITAL_SUCCESS_TAG = "HospitalInfo_Success"
        const val HOSPITAL_FAILED_TAG = "HospitalInfo_Failed"
        const val DOCTOR_SUCCESS_TAG = "QueryDoctorInfo_Success"
        const val DOCTOR_FAILED_TAG = "QueryDoctorInfo_Failed"

    }
}