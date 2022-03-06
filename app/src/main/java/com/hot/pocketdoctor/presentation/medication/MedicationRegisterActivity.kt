package com.hot.pocketdoctor.presentation.medication

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build

import android.os.Bundle
import android.text.format.DateFormat.format
import android.util.Log
import android.widget.CompoundButton
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.google.android.material.chip.Chip
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMedicationRegisterBinding
import com.hot.pocketdoctor.presentation.medication.RetrofitClient
import com.hot.pocketdoctor.presentation.reports.ReportsResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MedicationRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicationRegisterBinding
    lateinit var weekendList : Array<Boolean>
    private var week: MutableList<Int> = ArrayList()

    //lateinit var db: RoomHelper

    //Room에 정의된 Medicine구조체에 넣을 변수이름들
    var medicineName: String = ""
    var medicineExplain: String =""

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        db = Room.databaseBuilder(
            applicationContext,
            RoomHelper::class.java, "database-name"
        ).build()

        val userDao = db.roomMedicineDAO()
        val users: List<RoomMedicines> = userDao.getAll() //가져옴
         */
        limitChipGroupSelect()
        //시간설정
        setMedicationRegisterTime()
        //이름 입력
        getMedicineName()

        /*복약등록
        val saveBtn = binding.medicationRegisterButton
        saveBtn.setOnClickListener {

        }
        */
    }

    private fun getMedicineName(){
        with(binding){
            medicationNameEditText.addTextChangedListener {
                medicineName = it.toString()
                Log.d("약이름",medicineName)
            }
        }
    }

    private fun setMedicationRegisterTime(){
        val timeBtn = binding.medicationRegisterTimeBtn

        timeBtn.setOnClickListener {

            val cal = Calendar.getInstance()
            val startHour = cal.get(Calendar.HOUR_OF_DAY)
            val startMinute = cal.get(Calendar.MINUTE)

            with(binding) {

                weekendList = arrayOf(
                    sun.isChecked,
                    mon.isChecked,
                    tue.isChecked,
                    wed.isChecked,
                    thu.isChecked,
                    fri.isChecked,
                    sat.isChecked,
                )
            }
            Log.d("we",weekendList[0].toString())
            Log.d("we",weekendList[1].toString())
            Log.d("we",weekendList[2].toString())

            for (i in 0..6) {
                if (weekendList[i]) {
                    week.add(i + 1)
                    Log.d("add", (i + 1).toString())
                }
            }
            if( week.size == 1){
                week[1] = 0
                week[2] = 0
            }
            if( week.size == 2){
                week[2] = 0
            }
            val count = binding.chipGroup.checkedChipIds.size

            if( count >= 1) {
                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->

                        //설정시간 보이기 text
                        binding.registerTimeTextView3.setText("$hourOfDay:$minute")

                        val cal3 = Calendar.getInstance()
                        cal3.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal3.set(Calendar.MINUTE, minute)
                        cal3.set(Calendar.SECOND, 0)

                        val time3: SimpleDateFormat = SimpleDateFormat("hh:mm a")
                        binding.registerTimeTextView3.text = time3.format(cal3.time)

                        val intent = Intent(this, AlarmReceiver::class.java)

                        intent.putExtra("alarm3", true)
                        intent.putExtra("time1",week[0])
                        intent.putExtra("time2",week[1])
                        intent.putExtra("time3",week[2])

                        val alarmIntent =
                            PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_MUTABLE);

                        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

                        alarmManager.setExact(
                            AlarmManager.RTC_WAKEUP,
                            cal3.timeInMillis,
                            alarmIntent
                        )

                        Toast.makeText(this, "알람이 저장되었습니다 3.", Toast.LENGTH_LONG).show();
                    },
                    startHour,
                    startMinute,
                    true
                ).show()
                if (count >= 2) {
                    TimePickerDialog(
                        this,
                        TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                            //설정시간 보이기 text

                            val cal2 = Calendar.getInstance()
                            cal2.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            cal2.set(Calendar.MINUTE, minute)
                            cal2.set(Calendar.SECOND, 0)

                            val time2: SimpleDateFormat = SimpleDateFormat("hh:mm a")
                            binding.registerTimeTextView2.text = time2.format(cal2.time)

                            val intent = Intent(this, AlarmReceiver::class.java)

                            intent.putExtra("alarm2", true)
                            intent.putExtra("time1",week[0])
                            intent.putExtra("time2",week[1])
                            intent.putExtra("time3",week[2])

                            val alarmIntent = PendingIntent.getBroadcast(
                                this,
                                2,
                                intent,
                                PendingIntent.FLAG_MUTABLE
                            );

                            val alarmManager =
                                getSystemService(Context.ALARM_SERVICE) as AlarmManager

                            alarmManager.setExact(
                                AlarmManager.RTC_WAKEUP,
                                cal2.timeInMillis,
                                alarmIntent
                            )

                            Toast.makeText(this, "알람이 저장되었습니다 2.", Toast.LENGTH_LONG).show();
                        },
                        startHour,
                        startMinute,
                        true
                    ).show()

                    if (count >= 3) {
                        TimePickerDialog(
                            this,
                            TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->

                                val cal1 = Calendar.getInstance()
                                cal1.set(Calendar.HOUR_OF_DAY, hourOfDay)
                                cal1.set(Calendar.MINUTE, minute)
                                cal1.set(Calendar.SECOND, 0)

                                val time1: SimpleDateFormat = SimpleDateFormat("hh:mm a")
                                binding.registerTimeTextView1.text = time1.format(cal1.time)

                                val intent = Intent(this, AlarmReceiver::class.java)

                                intent.putExtra("alarm1", true)
                                intent.putExtra("time1",week[0])
                                intent.putExtra("time2",week[1])
                                intent.putExtra("time3",week[2])

                                val alarmIntent = PendingIntent.getBroadcast(
                                    this,
                                    1,
                                    intent,
                                    PendingIntent.FLAG_MUTABLE
                                );

                                val alarmManager =
                                    getSystemService(Context.ALARM_SERVICE) as AlarmManager

                                alarmManager.setExact(
                                    AlarmManager.RTC_WAKEUP,
                                    cal1.timeInMillis,
                                    alarmIntent
                                )

                                Toast.makeText(this, "알람이 저장되었습니다 1.", Toast.LENGTH_LONG).show();
                            },
                            startHour,
                            startMinute,
                            true
                        ).show()
                    }
                }
            }
        }
    }

    private fun limitChipGroupSelect(){
        with(binding){
            mon.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    mon.isChecked = false
                }
            }
            tue.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    tue.isChecked = false
                }
            }
            wed.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    wed.isChecked = false
                }
            }
            thu.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    thu.isChecked = false
                }
            }
            fri.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    fri.isChecked = false
                }
            }
            sat.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    sat.isChecked = false
                }
            }
            sun.setOnCheckedChangeListener { compoundButton, b ->
                if(chipGroup.checkedChipIds.size > 3){
                    sun.isChecked = false
                }
            }
        }
    }



    private fun postMedicine(){
        val medicationName : String = binding.medicationNameEditText.text.toString()
        val requestBody = MedicineRequestDTO( medicineName = medicationName)

        RetrofitClient.instance.postMedicine(requestBody)
            .enqueue(object : Callback<MedicineResponseDTO> {

                override fun onResponse(
                    call: Call<MedicineResponseDTO>,
                    response: Response<MedicineResponseDTO>
                ) {
                    if(response.isSuccessful) {
                        if(response.code() == 200){
                            val getMedicineData : MedicineResponseDTO? = response.body()
                            val medicineInfo = getMedicineData?.medicineInfo
                            //그리고 이 데이터를 ROOM 에 넣어야 함
                            medicineExplain = medicineInfo.toString()
                            //val message = getMedicineData?.responseMessage
                        }
                    }
                }
                override fun onFailure(call: Call<MedicineResponseDTO>, t: Throwable) {
                    Log.e("RETRO_ERR", "ERROR")
                }
            })
    }
}
