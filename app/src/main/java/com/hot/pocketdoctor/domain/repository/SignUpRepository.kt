package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.domain.model.SignUpData
import com.hot.pocketdoctor.domain.model.VerifyEmailData

interface SignUpRepository {
    suspend fun postSignUpResult(body: ReqSignUpSuccessData) : SignUpData

    suspend fun postVerifyEmail(body: ReqVerifyEmailSuccessData) : VerifyEmailData
}