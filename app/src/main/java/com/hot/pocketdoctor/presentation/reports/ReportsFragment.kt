package com.hot.pocketdoctor.presentation.reports


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference
import com.hot.pocketdoctor.databinding.FragmentReportsBinding
import com.hot.pocketdoctor.databinding.ItemReportsRecyclerBinding
import com.hot.pocketdoctor.presentation.medication.MedicationInfoActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportsFragment : Fragment() {

    private var _binding : FragmentReportsBinding? = null

    private val binding get() = _binding!!
    var reportList = listOf<ReportsList.Report>()

    //get 한 json 에서 추출한 userName
    var userName : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReportsBinding.inflate(inflater, container, false)

        if (PocketDoctorSharedPreference.getUserToken() !== "") {
            loadReports()
        } else {
            Toast.makeText(context, "Please Login First", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun setAdapter(){
        val adapter = ReportRecyclerAdapter(reportList)
        binding.ReportRecyclerview.adapter = adapter
        binding.ReportRecyclerview.layoutManager = LinearLayoutManager(this.activity)

        val intent = Intent(this.context,ReportsDetailActivity::class.java)

        adapter.setOnItemClickListener(object: ReportRecyclerAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("clickedReport_username",userName)
                intent.putExtra("clickedReport_doctor", reportList[position].doctor)
                intent.putExtra("clickedReport_hospital", reportList[position].hospital)
                intent.putExtra("clickedReport_reportTime", reportList[position].reportTime)
                intent.putExtra("clickedReport_symptom", reportList[position].symptom)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.iconMedicine.setOnClickListener {
            //Toast.makeText(this.activity,"click",Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this.activity, MedicationInfoActivity::class.java)
            startActivity(nextIntent)
        }
    }
    private fun loadReports() {

        RetrofitClient.instance.getReports().enqueue(object : Callback<ReportsResponseDTO> {
            override fun onResponse(
                call: Call<ReportsResponseDTO>, response: Response<ReportsResponseDTO>) {
                if(response.isSuccessful) {
                    if(response.code() == 200){

                        val getReportData : ReportsResponseDTO? = response.body()

                        getReportData?.let{
                            if(!it.reports.isNullOrEmpty()) {
                                userName = it.reports[0].userName
                                reportList = mappingReport(it.reports).reports //List<ReportData>?
                            }
                            setAdapter()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ReportsResponseDTO>, t: Throwable) {
                t.printStackTrace()
            }
        }
        )
    }

    private fun mappingReport(report: List<ReportsResponseDTO.ReportData>): ReportsList { //통신된 리스트
        //리스트의 요소를 mapping
        return ReportsList(
            reports = report.map {
                ReportsList.Report(
                    doctor = it.doctorName,
                    reportTime = it.reportTime,
                    symptom = it.description,
                    hospital = it.hospitalName,
                )
            }
        )

    }
}
class ReportRecyclerAdapter(private val reportData: List<ReportsList.Report>) : RecyclerView.Adapter<ReportRecyclerAdapter.Holder>() {

    //커스텀 리스너 인터페이스 정의
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    //전달된 객체를 저장할 변수 정의
    private lateinit var itemClickListener: OnItemClickListener

    //리스너 인터페이스 객체를 전달하는 메서드 선언
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }

    inner class Holder(val binding: ItemReportsRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.cardView.setOnClickListener {

                val pos = adapterPosition
                if(pos != RecyclerView.NO_POSITION){
                    itemClickListener.onItemClick(binding.cardView,pos)
                }
            }
        }
        fun setReport(report: ReportsList.Report){
            with(binding){
                idReportsDoctor.text = report.doctor
                idReportsDate.text = report.reportTime
                idReportsSymptom.text = report.symptom
                idReportsHospital.text = report.hospital
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): ReportRecyclerAdapter.Holder {
        val binding = ItemReportsRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: ReportRecyclerAdapter.Holder, position: Int) {
        val report = reportData.get(position)
        holder.setReport(report)
    }
    override fun getItemCount() = reportData.size
}