package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.datasource.signup.SignUpDataSource
import com.hot.pocketdoctor.data.datasource.signup.SignUpDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignUpDataSource> { SignUpDataSourceImpl(get()) }
}