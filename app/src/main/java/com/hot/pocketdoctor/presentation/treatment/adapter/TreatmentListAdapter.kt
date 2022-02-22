package com.hot.pocketdoctor.presentation.treatment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ItemTreatmentListBinding
import com.hot.pocketdoctor.domain.model.info.DoctorInfoData
import com.hot.pocketdoctor.presentation.treatment.model.DoctorListData

class TreatmentListAdapter(): RecyclerView.Adapter<TreatmentListAdapter.TreatmentListViewHolder>() {

    private val _doctorList = mutableListOf<DoctorListData>()
    var doctorList: List<DoctorListData> = _doctorList
        set(value) {
            _doctorList.clear()
            _doctorList.addAll(value)
            notifyDataSetChanged()
        }

    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: ItemTreatmentListBinding, position: Int)
    }

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentListAdapter.TreatmentListViewHolder {
        val binding = ItemTreatmentListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TreatmentListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TreatmentListAdapter.TreatmentListViewHolder, position: Int) {
        holder.bindItems(_doctorList[position])
    }

    override fun getItemCount(): Int = _doctorList.size

    inner class TreatmentListViewHolder(
        val binding: ItemTreatmentListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rlTreatmentItem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                    itemClickListener.onItemClick(binding, position)
                }
            }
        }

        fun bindItems(data: DoctorListData) {
            binding.doctorList = data
        }
    }
}