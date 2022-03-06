package com.hot.pocketdoctor.presentation.medication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMedicationInfoBinding
import com.hot.pocketdoctor.databinding.ItemMedicationBinding
import com.hot.pocketdoctor.presentation.medication.MedicationRegisterActivity

class MedicationInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medicationData = loadMedicinesData()
        val customAdapter = CustomAdapter()
        customAdapter.medicinelist = medicationData
        binding.MedicationRecycler.adapter = customAdapter
        binding.MedicationRecycler.layoutManager = LinearLayoutManager(this)

        val fab = binding.medicationInfoAddFab
        fab.setOnClickListener {
            val nextIntent = Intent(this, MedicationRegisterActivity::class.java)
            startActivity(nextIntent)
        }
    }

    fun loadMedicinesData() : MutableList<Medicine> {
        val medicineList = mutableListOf<Medicine>()
        for(i in 1..10) {
            val medicineName = "약 B"
            val medicineCount = 2
            val medicineReservationTime = "11:00 19:00"
            val medicineExplain = "해열제"
            val medicine = Medicine(medicineName,medicineCount, medicineReservationTime, medicineExplain)
            medicineList.add(medicine)
        }
        return medicineList
    }
}

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.Holder>() {

    var medicinelist = mutableListOf<Medicine>()

    class Holder(val binding: ItemMedicationBinding): RecyclerView.ViewHolder(binding.root){
        fun setMedicine(medicine:Medicine){
            with(binding){
                idMedicationName.text = medicine.medicine_name
                idMedicationTimes.text = "주 "+"${medicine.medicine_count}"+"회 "+medicine.medicine_reservation_time
                idMedicationEffect.text = medicine.medicine_explain
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.Holder {
        val binding = ItemMedicationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: CustomAdapter.Holder, position: Int) {
        val medicine = medicinelist.get(position)
        holder.setMedicine(medicine)
    }

    override fun getItemCount() = medicinelist.size
}
