package com.hot.pocketdoctor.presentation.reports


import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ReportsFragment : Fragment() {

    private var _binding : FragmentReportsBinding? = null
    private val binding get() = _binding!!

    //get 한 json 에서 추출한 userName
    var userName : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReportsBinding.inflate(inflater, container, false)

        loadReports()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reports = loadReportData()
        val reportRecyclerAdapter = ReportRecyclerAdapter(reports)
        binding.ReportRecyclerview.adapter = reportRecyclerAdapter
        binding.ReportRecyclerview.layoutManager = LinearLayoutManager(this.activity)
        binding.iconMedicine.setOnClickListener {
            Toast.makeText(this.activity,"click",Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this.activity, MedicationInfoActivity::class.java)
            startActivity(nextIntent)
        }
        //아이템 클릭리스너
        val intent = Intent(this.context,ReportsDetailActivity::class.java)
        reportRecyclerAdapter.setOnItemclickListner(object: ReportRecyclerAdapter.OnItemClickListner{
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("clickedReport_username",userName)
                intent.putExtra("clickedReport_doctor", reports[position].doctor)
                intent.putExtra("clickedReport_hospital", reports[position].hospital)
                intent.putExtra("clickedReport_reportTime", reports[position].reportTime)
                intent.putExtra("clickedReport_symptom", reports[position].symptom)
                startActivity(intent)
            }
        })
    }
    private fun loadReports(){
        val reportList = mutableListOf<Reports>()
        //val call: Call<ReportsResponseDTO> = RetrofitClient.instance.getReports()
        //    call.enqueue(object : retrofit2.Callback<ReportsResponseDTO>{
        RetrofitClient.instance.getReports().enqueue(object : Callback<ReportsResponseDTO> {
            override fun onResponse(
                    call: Call<ReportsResponseDTO>, response: Response<ReportsResponseDTO>) {
                    if(response.isSuccessful) {
                        if(response.code() == 200){

                            val getReportData : ReportsResponseDTO? = response.body()
                            val reportDataList = getReportData?.reports

                            if(reportDataList?.isNotEmpty() == true) {
                                for (i in 1..reportDataList.size) {
                                    val report = reportDataList[i]
                                    userName = report.userName
                                    val receivedDoctorName: String = report.doctorName
                                    val receivedHospitalName = report.hospitalName
                                    val receivedReportTime = report.reportTime
                                    val receivedDescription = report.description

                                    val receivedReport = Reports(receivedDoctorName, receivedReportTime, receivedDescription, receivedHospitalName)
                                    reportList.add(receivedReport)
                                }
                            }
                        }
                    }
                }
                override fun onFailure(call: Call<ReportsResponseDTO>, t: Throwable) {
                    Log.e("RETRO_ERR", "ERROR")
                }
            }
        )
    }
    private fun loadReportData() : MutableList<Reports> {
        val reportList = mutableListOf<Reports>()
        for(i in 1..10){
            val doctor = "이재성 의사 ${i}"
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
    //커스텀 리스너 인터페이스 정의
    interface OnItemClickListner{
        fun onItemClick(view: View, position: Int)
    }
    //리스너 인터페이스 객체를 전달하는 메서드 선언
    fun setOnItemclickListner(onItemClickListner: OnItemClickListner){
        itemClickListner = onItemClickListner
    }

    //전달된 객체를 저장할 변수 정의
    private lateinit var itemClickListner: OnItemClickListner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ReportRecyclerAdapter.Holder {
        val binding = ItemReportsRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: ReportRecyclerAdapter.Holder, position: Int) {
        val report = reportData.get(position)
        holder.setReport(report)
    }
    override fun getItemCount() = reportData.size

    inner class Holder(val binding: ItemReportsRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cardView.setOnClickListener {

                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION && itemClickListner != null){
                    itemClickListner.onItemClick(binding.cardView,pos)
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