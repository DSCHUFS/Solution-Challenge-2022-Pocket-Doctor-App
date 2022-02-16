package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.repository.SignInRepositoryImpl
import com.hot.pocketdoctor.data.repository.SignUpRepositoryImpl
import com.hot.pocketdoctor.data.repository.info.DoctorInfoRepositoryImpl
import com.hot.pocketdoctor.domain.repository.DoctorInfoRepository
import com.hot.pocketdoctor.domain.repository.SignInRepository
import com.hot.pocketdoctor.domain.repository.SignUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SignUpRepository> { SignUpRepositoryImpl(get()) }
    single<SignInRepository> { SignInRepositoryImpl(get()) }
    single<DoctorInfoRepository> { DoctorInfoRepositoryImpl(get()) }
}