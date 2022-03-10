package com.hot.pocketdoctor.data.datasource.user.login

import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.data.model.response.user.signin.ResSignInSuccessData

interface SignInDataSource {
    suspend fun postSignIn(body: ReqSignInSuccessData) : ResSignInSuccessData
}