package com.hot.pocketdoctor.presentation.treatment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ItemTreatmentListBinding
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData

class TreatmentListAdapter: RecyclerView.Adapter<TreatmentListAdapter.TreatmentListViewHolder>() {

    private var _doctorList : MutableList<DoctorInfoData.DoctorInfo> = mutableListOf()
    var doctorList: List<DoctorInfoData.DoctorInfo> = _doctorList

    private lateinit var itemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(view: View)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentListAdapter.TreatmentListViewHolder {
        val binding: ItemTreatmentListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_treatment_list, parent, false
        )
        return TreatmentListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TreatmentListAdapter.TreatmentListViewHolder, position: Int) {
        holder.bindItems(doctorList[position])

        holder.binding.rlTreatmentItem.setOnClickListener {
            itemClickListener.onItemClick(it)
        }
    }

    override fun getItemCount(): Int = doctorList.size

    inner class TreatmentListViewHolder(
        val binding: ItemTreatmentListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItems(data: DoctorInfoData.DoctorInfo) {
            binding.doctorList = data
            notifyDataSetChanged()
        }
    }
}