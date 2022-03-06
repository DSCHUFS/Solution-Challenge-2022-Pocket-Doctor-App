package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.signin.ResSignInSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResVerifyEmailSuccessData
import com.hot.pocketdoctor.domain.model.SignInData
import com.hot.pocketdoctor.domain.model.SignUpData
import com.hot.pocketdoctor.domain.model.VerifyEmailData

object UserAuthMapper {

    fun mapperToSignUpResultData(resSignUpSuccessData: ResSignUpSuccessData): SignUpData {
        return SignUpData(
            message = resSignUpSuccessData.message,
            status = resSignUpSuccessData.status
        )
    }

    fun mapperToSignInResultData(resSignInSuccessData: ResSignInSuccessData): SignInData {
        return SignInData(
            token = resSignInSuccessData.token,
            message = resSignInSuccessData.message,
            status = resSignInSuccessData.status
        )
    }

    fun mapperToVerifyEmailResultData(resVerifyEmailSuccessData: ResVerifyEmailSuccessData): VerifyEmailData {
        return VerifyEmailData(
            verificationCode = resVerifyEmailSuccessData.verificationCode
        )
    }
}