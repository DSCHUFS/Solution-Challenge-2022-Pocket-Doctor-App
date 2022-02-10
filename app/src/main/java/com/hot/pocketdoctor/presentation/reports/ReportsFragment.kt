package com.hot.pocketdoctor.presentation.reports

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.databinding.FragmentReportsBinding
import com.hot.pocketdoctor.databinding.ItemReportsRecyclerBinding
import com.hot.pocketdoctor.presentation.medication.MedicationInfoActivity
import com.hot.pocketdoctor.presentation.medication.MedicationRegisterActivity

class ReportsFragment : Fragment() {

    private var _binding : FragmentReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReportsBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reports = loadReportData()
        val reportRecyclerAdapter = ReportRecyclerAdapter(reports)
        binding.ReportRecyclerview.adapter = reportRecyclerAdapter
        binding.ReportRecyclerview.layoutManager = LinearLayoutManager(this.activity)
        binding.iconMedicine.setOnClickListener {
            //Toast.makeText(this.activity,"click",Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this.activity, MedicationInfoActivity::class.java)
            startActivity(nextIntent)
        }
    }
    private fun loadReportData() : MutableList<Reports> {
        val reportList = mutableListOf<Reports>()
        for(i in 1..10){
            val doctor = "이재성 의사"
            val reportTime = "2022.01.10 (월) 17:38"
            val symptom = "다 때려 부수고 싶어요"
            val hospital = "더푸른 의원"
            val report = Reports(doctor, reportTime, symptom, hospital)
            reportList.add(report)
        }
        return reportList
    }
}
class ReportRecyclerAdapter(val reportData: MutableList<Reports>) : RecyclerView.Adapter<ReportRecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ReportRecyclerAdapter.Holder {
        val binding = ItemReportsRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: ReportRecyclerAdapter.Holder, position: Int) {
        val report = reportData.get(position)
        holder.setReport(report)
    }
    override fun getItemCount() = reportData.size

    class Holder(val binding: ItemReportsRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                with(binding){
                    val reportDoctor = idReportsDoctor.text
                    val reportDate = idReportsDate.text
                    val reportSymptom = idReportsSymptom.text
                    val reportHospital = idReportsHospital.text
                }
            }
        }
        fun setReport(report: Reports){
            with(binding){
                idReportsDoctor.text = report.doctor
                idReportsDate.text = report.reportTime
                idReportsSymptom.text = report.symptom
                idReportsHospital.text = report.hospital
            }
        }
    }
}