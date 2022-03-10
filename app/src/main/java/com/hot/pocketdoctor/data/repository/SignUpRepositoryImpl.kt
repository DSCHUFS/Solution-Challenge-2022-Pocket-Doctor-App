package com.hot.pocketdoctor.data.repository

import com.hot.pocketdoctor.data.datasource.user.signup.SignUpDataSource
import com.hot.pocketdoctor.data.mapper.UserAuthMapper
import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.domain.model.SignUpData
import com.hot.pocketdoctor.domain.model.VerifyEmailData
import com.hot.pocketdoctor.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun postSignUpResult(body: ReqSignUpSuccessData): SignUpData {
        return UserAuthMapper.mapperToSignUpResultData(signUpDataSource.postSignUp(body))
    }

    override suspend fun postVerifyEmail(body: ReqVerifyEmailSuccessData): VerifyEmailData {
        return UserAuthMapper.mapperToVerifyEmailResultData(signUpDataSource.postVerifyEmail(body))
    }
}