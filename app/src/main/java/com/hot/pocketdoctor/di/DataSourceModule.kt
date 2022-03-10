package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.datasource.user.login.SignInDataSource
import com.hot.pocketdoctor.data.datasource.user.login.SignInDataSourceImpl
import com.hot.pocketdoctor.data.datasource.user.signup.SignUpDataSource
import com.hot.pocketdoctor.data.datasource.user.signup.SignUpDataSourceImpl
import com.hot.pocketdoctor.data.datasource.treatment.*
import com.hot.pocketdoctor.data.datasource.user.UserInfoDataSource
import com.hot.pocketdoctor.data.datasource.user.UserInfoDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignUpDataSource> { SignUpDataSourceImpl(get()) }
    single<SignInDataSource> { SignInDataSourceImpl(get()) }
    single<DoctorInfoDataSource> { DoctorInfoDataSourceImpl(get()) }
    single<HospitalInfoDataSource> { HospitalInfoDataSourceImpl(get()) }
    single<ReservationDataSource> { ReservationDataSourceImpl(get()) }
    single<UserInfoDataSource> { UserInfoDataSourceImpl(get()) }
}