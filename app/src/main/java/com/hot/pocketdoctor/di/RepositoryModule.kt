package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.repository.SignUpRepositoryImpl
import com.hot.pocketdoctor.domain.repository.SignUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<SignUpRepository> { SignUpRepositoryImpl(get()) }
}