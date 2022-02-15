package com.hot.pocketdoctor.data.datasource.login

import com.hot.pocketdoctor.data.api.UserAuthService
import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.data.model.response.signin.ResSignInSuccessData

class SignInDataSourceImpl(val userAuthService: UserAuthService) : SignInDataSource {
    override suspend fun postSignIn(body: ReqSignInSuccessData): ResSignInSuccessData {
        return userAuthService.postSignIn(body)
    }
}