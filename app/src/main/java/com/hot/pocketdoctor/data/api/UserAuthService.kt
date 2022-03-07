package com.hot.pocketdoctor.data.api

import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.data.model.response.signin.ResSignInSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.signup.ResVerifyEmailSuccessData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthService {

    @POST("/user/signup")
    suspend fun postSignUp(
        @Body body: ReqSignUpSuccessData
    ) : ResSignUpSuccessData

    @POST("/user/signin")
    suspend fun postSignIn(
        @Body body: ReqSignInSuccessData
    ) : ResSignInSuccessData

    @POST("/email")
    suspend fun postVerifyEmail(
        @Body body: ReqVerifyEmailSuccessData
    ) : ResVerifyEmailSuccessData
}