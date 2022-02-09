package com.hot.pocketdoctor.presentation.treatment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.databinding.ItemTreatmentListBinding

class TreatmentPagerAdapter(val list: List<String>) : RecyclerView.Adapter<TreatmentPagerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentPagerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTreatmentListBinding.inflate(layoutInflater, parent, false)
        return TreatmentPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: TreatmentPagerHolder, position: Int) {
        val text = list[position]
        holder.bindViews(text)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}