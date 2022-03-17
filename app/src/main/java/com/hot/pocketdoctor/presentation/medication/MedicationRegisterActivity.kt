package com.hot.pocketdoctor.presentation.medication

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.hot.pocketdoctor.databinding.ActivityMedicationRegisterBinding
import com.hot.pocketdoctor.presentation.reports.RetrofitClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class MedicationRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicationRegisterBinding
    lateinit var weekendList : Array<Boolean>
    private var db : RoomHelper? = null
    private var count : Int = 0
    private var week: MutableList<Int> = ArrayList()
    private val cal1: Calendar = Calendar.getInstance()
    private val cal2: Calendar = Calendar.getInstance()
    private val cal3: Calendar = Calendar.getInstance()
    val time = SimpleDateFormat("hh:mm")

    private var medicineName: String = " "
    var reservationTime: String = "  "
    var medicineExplain: String =" "


    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicationRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomHelper.getInstance(this)

        limitChipGroupSelect()
        //시간설정
        setMedicationRegisterTimes()

        val addRunnable = Runnable {

            val newDB = RoomMedicine(medicineName, count, reservationTime, medicineExplain)
            db?.roomMedicineDAO()?.insert(newDB)
        }

        //복약등록
        val saveBtn = binding.medicationRegisterButton
        saveBtn.setOnClickListener {

            setAlarm()
            //이름 입력
            medicineName = binding.medicationNameEditText.text.toString()

            //입력된 이름의 약 정보 통신해서 가져오기
            //postMedicine()

            runBlocking {
                val a = launch{
                    val requestBody = MedicineRequestDTO( medicineName = medicineName)
                    val response = RetrofitClient.instance.postMedicine(requestBody)
                    if(response.isSuccessful){
                        if(response.code() == 200) {
                            val getMedicineData: MedicineResponseDTO? = response.body()
                            val medicineInfo = getMedicineData?.medicineInfo
                            //그리고 이 데이터를 ROOM 에 넣어야 함
                            medicineExplain = medicineInfo.toString()
                        }
                    }
                    else{
                        Toast.makeText(baseContext, "Please try later.", Toast.LENGTH_SHORT).show()
                    }
                }
                a.join()
            }

            val addThread = Thread(addRunnable)
            addThread.start()

            val nextIntent = Intent(this, MedicationInfoActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
    }

    override fun onDestroy() {
        RoomHelper.destroyInstance()
        super.onDestroy()
    }



    private fun setMedicationRegisterTimes(){
        val timeBtn = binding.medicationRegisterTimeBtn
        val cal = Calendar.getInstance()
        val startHour = cal.get(Calendar.HOUR_OF_DAY)
        val startMinute = cal.get(Calendar.MINUTE)

        timeBtn.setOnClickListener {
            if(!binding.registerTimeTextView1.isEnabled) {

                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->

                        cal1.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal1.set(Calendar.MINUTE, minute)
                        cal1.set(Calendar.SECOND, 0)

                        val time1 = SimpleDateFormat("hh:mm a")


                        reservationTime += time.format(cal1.time)
                        binding.registerTimeTextView1.text = time1.format(cal1.time)
                        binding.registerTimeTextView1.isEnabled = true

                    },
                    startHour,
                    startMinute,
                    true
                ).show()
            }
            //2
            if(binding.registerTimeTextView1.isEnabled && (!binding.registerTimeTextView2.isEnabled)){

                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->

                        cal2.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal2.set(Calendar.MINUTE, minute)
                        cal2.set(Calendar.SECOND, 0)

                        val time2 = SimpleDateFormat("hh:mm a")

                        reservationTime +="  "
                        reservationTime += time.format(cal2.time)
                        binding.registerTimeTextView2.text = time2.format(cal2.time)
                        binding.registerTimeTextView2.isEnabled = true
                    },
                    startHour,
                    startMinute,
                    true
                ).show()
            }
            //3
            if(binding.registerTimeTextView2.isEnabled && (!binding.registerTimeTextView3.isEnabled)){

                TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->

                        cal3.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        cal3.set(Calendar.MINUTE, minute)
                        cal3.set(Calendar.SECOND, 0)

                        val time3 = SimpleDateFormat("hh:mm a")

                        reservationTime += "  "
                        reservationTime += time.format(cal3.time)
                        binding.registerTimeTextView3.text = time3.format(cal3.time)
                        binding.registerTimeTextView3.isEnabled = true
                    },
                    startHour,
                    startMinute,
                    true
                ).show()
            }
        }
    }
    //
    private fun setAlarm() {
        //주 몇 회
        count = binding.chipGroup.checkedChipIds.size

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
        for (i in 0..6) {
            if (weekendList[i]) {
                week.add(i + 1) //선택된 요일
            }
        }
        if (week.size == 0) {
            week.add(0,0)
            week.add(1,0)
            week.add(2,0)
        }
        if (week.size == 1) {
            week.add(1,0)
            week.add(2,0)
        }
        if (week.size == 2) {
            week.add(2,0)
        }

        val intent = Intent(this, AlarmReceiver::class.java)

        //요일 전달
        intent.putExtra("time1", week[0])
        intent.putExtra("time2", week[1])
        intent.putExtra("time3", week[2])

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //일 횟수에 따른 알림 설정
        if (binding.registerTimeTextView1.isEnabled) {

            intent.putExtra("alarm1",true)
            val alarmIntent1 =
                PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_MUTABLE);

            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                cal1.timeInMillis,
                alarmIntent1
            )

            if (binding.registerTimeTextView2.isEnabled) {

                intent.putExtra("alarm2",true)
                val alarmIntent2 =
                    PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_MUTABLE);

                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    cal2.timeInMillis,
                    alarmIntent2
                )

                if (binding.registerTimeTextView3.isEnabled) {

                    intent.putExtra("alarm3",true)
                    val alarmIntent3 =
                        PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_MUTABLE);

                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        cal3.timeInMillis,
                        alarmIntent3
                    )
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
}
