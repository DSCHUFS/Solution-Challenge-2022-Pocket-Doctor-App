package com.hot.pocketdoctor.presentation.medication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hot.pocketdoctor.databinding.ActivityMedicationInfoBinding
import com.hot.pocketdoctor.databinding.ItemMedicationBinding

class MedicationInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicationInfoBinding
    lateinit var customAdapter: CustomAdapter
    var medicineReservationList = listOf<RoomMedicine>()
    private var db : RoomHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomHelper.getInstance(this)


        val r = Runnable {
            try {
                medicineReservationList = db?.roomMedicineDAO()?.getAll()!!

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val thread = Thread(r)
        thread.start()
        thread.join()

        setAdapter()
        val fab = binding.medicationInfoAddFab
        fab.setOnClickListener {
            val nextIntent = Intent(this, MedicationRegisterActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
    }

    private fun setAdapter(){
        customAdapter = CustomAdapter(medicineReservationList)
        customAdapter.notifyDataSetChanged()

        binding.MedicationRecycler.adapter = customAdapter
        binding.MedicationRecycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        RoomHelper.destroyInstance()
        //db = null
        super.onDestroy()
    }
}

class CustomAdapter(val medicineList: List<RoomMedicine>) : RecyclerView.Adapter<CustomAdapter.Holder>() {

    class Holder(val binding: ItemMedicationBinding): RecyclerView.ViewHolder(binding.root){
        fun setMedicine(medicine:RoomMedicine){
            with(binding){
                idMedicationName.text = medicine.medicineName
                idMedicationTimes.text = "??? "+"${medicine.medicineReservationDay}"+"??? "+medicine.medicineReservationTime
                idMedicationEffect.text = medicine.medicineInfo
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMedicationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val medicine = medicineList.get(position)
        holder.setMedicine(medicine)
    }

    override fun getItemCount() = medicineList.size
}
