package com.hot.pocketdoctor.data.datasource.signup

import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResVerifyEmailSuccessData

interface SignUpDataSource {
    suspend fun postSignUp(body: ReqSignUpSuccessData) : ResSignUpSuccessData

    suspend fun postVerifyEmail(body: ReqVerifyEmailSuccessData) : ResVerifyEmailSuccessData
}