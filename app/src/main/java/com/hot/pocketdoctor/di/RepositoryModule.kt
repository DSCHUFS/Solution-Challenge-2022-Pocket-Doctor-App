package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.repository.SignInRepositoryImpl
import com.hot.pocketdoctor.data.repository.SignUpRepositoryImpl
import com.hot.pocketdoctor.data.repository.UserInfoRepositoryImpl
import com.hot.pocketdoctor.data.repository.treatment.DoctorInfoRepositoryImpl
import com.hot.pocketdoctor.data.repository.treatment.HospitalInfoRepositoryImpl
import com.hot.pocketdoctor.data.repository.treatment.ReservationRepositoryImpl
import com.hot.pocketdoctor.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<SignUpRepository> { SignUpRepositoryImpl(get()) }
    single<SignInRepository> { SignInRepositoryImpl(get()) }
    single<DoctorInfoRepository> { DoctorInfoRepositoryImpl(get()) }
    single<HospitalInfoRepository> { HospitalInfoRepositoryImpl(get()) }
    single<ReservationRepository> { ReservationRepositoryImpl(get()) }
    single<UserInfoRepository> { UserInfoRepositoryImpl(get()) }
}