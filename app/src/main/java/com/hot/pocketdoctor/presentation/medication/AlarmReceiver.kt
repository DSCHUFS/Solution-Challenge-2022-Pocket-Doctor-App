package com.hot.pocketdoctor.presentation.medication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.hot.pocketdoctor.R
import com.hot.pocketdoctor.databinding.ActivityMedicationInfoBinding
import java.util.*
import java.util.Calendar.DAY_OF_WEEK
import java.util.Date.from
import kotlin.collections.ArrayList

class AlarmReceiver : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent) {


        val value1 = intent.getIntExtra("time1",0)
        val value2 = intent.getIntExtra("time2",0)
        val value3 = intent.getIntExtra("time3",0)

        val cal = Calendar.getInstance()

        if((cal.get(DAY_OF_WEEK) == value1)||(cal.get(DAY_OF_WEEK) == value2)||(cal.get(DAY_OF_WEEK) == value3) ) {
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "channel_name"
                val descriptionText = "channel_description"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel("notification_channel", name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                notificationManager.createNotificationChannel(channel)
            }
            val contentIntent = Intent(context, MedicationInfoActivity::class.java)
            val contentPendingIntent = PendingIntent.getActivity(
                context,
                0,
                contentIntent,
                PendingIntent.FLAG_MUTABLE
            )

            val builder = NotificationCompat.Builder(context, "notification_channel")
                .setSmallIcon(R.drawable.ic_logo_300)
                .setContentTitle("PocketDoctor")
                .setContentText("It's time to get Medicine!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(contentPendingIntent)

            if(intent.getBooleanExtra("alarm1",false) && !intent.getBooleanExtra("alarm2",false) && !intent.getBooleanExtra("alarm3",false)){
                with(NotificationManagerCompat.from(context)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(1, builder.build())
                }
            }
            if(intent.getBooleanExtra("alarm1",false) && intent.getBooleanExtra("alarm2",false) && !intent.getBooleanExtra("alarm3",false)){
                with(NotificationManagerCompat.from(context)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(2, builder.build())
                }
            }
            if(intent.getBooleanExtra("alarm1",false) && intent.getBooleanExtra("alarm2",false) && intent.getBooleanExtra("alarm3",false)){
                with(NotificationManagerCompat.from(context)) {
                    // notificationId is a unique int for each notification that you must define
                    notify(3, builder.build())
                }
            }
        }
        else{
            Log.d("today", cal.get(DAY_OF_WEEK).toString())
        }
    }
}
