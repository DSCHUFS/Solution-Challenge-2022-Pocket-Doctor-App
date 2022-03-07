package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.user.ResUserInfoSuccessData
import com.hot.pocketdoctor.data.model.response.user.signin.ResSignInSuccessData
import com.hot.pocketdoctor.data.model.response.user.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.user.signup.ResVerifyEmailSuccessData
import com.hot.pocketdoctor.domain.model.SignInData
import com.hot.pocketdoctor.domain.model.SignUpData
import com.hot.pocketdoctor.domain.model.UserInfoData
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
            verificationCode = resVerifyEmailSuccessData.verificationCode,
            verifiedEmail = resVerifyEmailSuccessData.verifiedEmail
        )
    }

    fun mapperToUserInfoData(resUserInfoSuccessData: ResUserInfoSuccessData) : UserInfoData {
        return UserInfoData(
            userEmail = resUserInfoSuccessData.userEmail,
            userName = resUserInfoSuccessData.userName,
            userPhoneNum = resUserInfoSuccessData.userPhoneNum
        )
    }
}