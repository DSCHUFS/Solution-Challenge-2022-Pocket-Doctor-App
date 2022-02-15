package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.datasource.login.SignInDataSource
import com.hot.pocketdoctor.data.datasource.login.SignInDataSourceImpl
import com.hot.pocketdoctor.data.datasource.signup.SignUpDataSource
import com.hot.pocketdoctor.data.datasource.signup.SignUpDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<SignUpDataSource> { SignUpDataSourceImpl(get()) }
    single<SignInDataSource> { SignInDataSourceImpl(get()) }
}