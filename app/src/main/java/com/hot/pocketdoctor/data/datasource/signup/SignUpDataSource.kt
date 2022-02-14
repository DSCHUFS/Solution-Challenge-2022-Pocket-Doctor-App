package com.hot.pocketdoctor.data.datasource.signup

import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData

interface SignUpDataSource {
    suspend fun postSignUp(body: ReqSignUpSuccessData) : ResSignUpSuccessData
}