package com.hot.pocketdoctor.data.mapper

import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.domain.model.SignUpData

object UserAuthMapper {

    fun mapperToSignUpResultData(resSignUpSuccessData: ResSignUpSuccessData) : SignUpData {
        return SignUpData(
            message = resSignUpSuccessData.message,
            code = resSignUpSuccessData.code
        )
    }
}