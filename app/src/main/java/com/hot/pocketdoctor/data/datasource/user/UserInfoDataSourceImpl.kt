package com.hot.pocketdoctor.data.datasource.user

import com.hot.pocketdoctor.data.api.UserAuthService
import com.hot.pocketdoctor.data.model.response.user.ResUserInfoSuccessData

class UserInfoDataSourceImpl(val userAuthService: UserAuthService) : UserInfoDataSource{
    override suspend fun getUserInfo(): ResUserInfoSuccessData {
        return userAuthService.getUserInfo()
    }
}