package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.datasource.login.SignInDataSource
import com.hot.pocketdoctor.data.datasource.login.SignInDataSourceImpl
import com.hot.pocketdoctor.data.datasource.signup.SignUpDataSource
import com.hot.pocketdoctor.data.datasource.signup.SignUpDataSourceImpl
import com.hot.pocketdoctor.data.datasource.treatment.*
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignUpDataSource> { SignUpDataSourceImpl(get()) }
    single<SignInDataSource> { SignInDataSourceImpl(get()) }
    single<DoctorInfoDataSource> { DoctorInfoDataSourceImpl(get()) }
    single<HospitalInfoDataSource> { HospitalInfoDataSourceImpl(get()) }
    single<ReservationDataSource> { ReservationDataSourceImpl(get()) }
}