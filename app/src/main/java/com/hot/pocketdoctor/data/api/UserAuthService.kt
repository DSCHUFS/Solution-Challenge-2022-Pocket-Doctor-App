package com.hot.pocketdoctor.data.api

import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqSignUpSuccessData
import com.hot.pocketdoctor.data.model.request.signup.ReqVerifyEmailSuccessData
import com.hot.pocketdoctor.data.model.response.user.ResUserInfoSuccessData
import com.hot.pocketdoctor.data.model.response.user.signin.ResSignInSuccessData
import com.hot.pocketdoctor.data.model.response.user.signup.ResSignUpSuccessData
import com.hot.pocketdoctor.data.model.response.user.signup.ResVerifyEmailSuccessData
import retrofit2.http.Body
import retrofit2.http.GET
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

    @GET("/user")
    suspend fun getUserInfo() : ResUserInfoSuccessData
}