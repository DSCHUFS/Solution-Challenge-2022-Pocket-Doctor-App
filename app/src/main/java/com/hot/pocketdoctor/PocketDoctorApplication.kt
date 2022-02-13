package com.hot.pocketdoctor

import android.app.Application
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference
import com.hot.pocketdoctor.di.dataSourceModule
import com.hot.pocketdoctor.di.networkModule
import com.hot.pocketdoctor.di.repositoryModule
import com.hot.pocketdoctor.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PocketDoctorApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@PocketDoctorApplication)
            modules(networkModule, repositoryModule, dataSourceModule, viewModelModule)
        }

        PocketDoctorSharedPreference.init(applicationContext)
    }
}