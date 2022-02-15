package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.domain.model.SignInData

interface SignInRepository {
    suspend fun postSignInResult(body: ReqSignInSuccessData) : SignInData
}