package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.domain.model.SignUpData

interface SignUpRepository {
    suspend fun postSignUpResult(body: ReqSignUpSuccessData) : SignUpData
}