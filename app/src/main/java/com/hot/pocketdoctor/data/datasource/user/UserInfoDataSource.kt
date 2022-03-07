package com.hot.pocketdoctor.data.datasource.user

import com.hot.pocketdoctor.data.model.response.user.ResUserInfoSuccessData

interface UserInfoDataSource {
    suspend fun getUserInfo() : ResUserInfoSuccessData
}