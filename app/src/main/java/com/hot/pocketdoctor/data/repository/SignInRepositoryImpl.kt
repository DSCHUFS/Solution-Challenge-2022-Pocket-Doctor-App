package com.hot.pocketdoctor.data.repository

import com.hot.pocketdoctor.data.datasource.user.login.SignInDataSource
import com.hot.pocketdoctor.data.mapper.UserAuthMapper
import com.hot.pocketdoctor.data.model.request.signin.ReqSignInSuccessData
import com.hot.pocketdoctor.domain.model.SignInData
import com.hot.pocketdoctor.domain.repository.SignInRepository

class SignInRepositoryImpl(private val signInDataSource: SignInDataSource) : SignInRepository {
    override suspend fun postSignInResult(body: ReqSignInSuccessData): SignInData {
        return UserAuthMapper.mapperToSignInResultData(signInDataSource.postSignIn(body))
    }
}