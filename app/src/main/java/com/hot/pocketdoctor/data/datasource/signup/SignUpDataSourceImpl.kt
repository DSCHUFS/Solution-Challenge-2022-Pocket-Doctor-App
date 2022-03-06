package com.hot.pocketdoctor.data.datasource.signup

import com.hot.pocketdoctor.data.api.UserAuthService
import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResVerifyEmailSuccessData

class SignUpDataSourceImpl(val userAuthService: UserAuthService) : SignUpDataSource {

    override suspend fun postSignUp(body: ReqSignUpSuccessData): ResSignUpSuccessData {
        return userAuthService.postSignUp(body)
    }

    override suspend fun postVerifyEmail(body: ReqVerifyEmailSuccessData): ResVerifyEmailSuccessData {
        return userAuthService.postVerifyEmail(body)
    }
}