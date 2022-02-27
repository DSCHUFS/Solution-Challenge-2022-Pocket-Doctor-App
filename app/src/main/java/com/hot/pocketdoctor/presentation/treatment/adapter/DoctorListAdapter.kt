package com.hot.pocketdoctor.presentation.treatment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.databinding.ItemTreatmentListBinding
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData
import com.hot.pocketdoctor.presentation.treatment.TreatmentListFragmentDirections
import com.hot.pocketdoctor.util.navigateWithData

class DoctorListAdapter :
    ListAdapter<DoctorInfoData.DoctorInfo, DoctorListAdapter.DoctorListViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorListViewHolder {
        val binding =
            ItemTreatmentListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorListViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    class DoctorListViewHolder(
        private val binding: ItemTreatmentListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(data: DoctorInfoData.DoctorInfo) {
            binding.doctorInfo = data

            setRootViewClickListener(
                data.doctorNo,
                data.doctorName,
                data.hospitalName,
                data.subject
            )
        }

        private fun setRootViewClickListener(
            doctorNo: Int,
            doctorName: String,
            hospitalName: String,
            subject: String
        ) {
            binding.rlTreatmentItem.setOnClickListener { view ->
                view.navigateWithData(
                    TreatmentListFragmentDirections.actionTreatmentListFragmentToHospitalDetailFragment(
                        doctorNo, doctorName, hospitalName, subject
                    )
                )
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DoctorInfoData.DoctorInfo>() {
            override fun areItemsTheSame(
                oldItem: DoctorInfoData.DoctorInfo,
                newItem: DoctorInfoData.DoctorInfo
            ): Boolean {
                return oldItem.doctorNo == newItem.doctorNo
            }

            override fun areContentsTheSame(
                oldItem: DoctorInfoData.DoctorInfo,
                newItem: DoctorInfoData.DoctorInfo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}