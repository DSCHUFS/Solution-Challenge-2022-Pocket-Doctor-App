package com.hot.pocketdoctor.data.api

import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthService {

    @POST("/user/signup")
    suspend fun postSignUp(
        @Body body : ReqSignUpSuccessData
    ) : ResSignUpSuccessData
}