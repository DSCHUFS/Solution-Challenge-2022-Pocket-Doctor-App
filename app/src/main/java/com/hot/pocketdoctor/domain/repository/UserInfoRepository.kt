package com.hot.pocketdoctor.domain.repository

import com.hot.pocketdoctor.domain.model.UserInfoData

interface UserInfoRepository {
    suspend fun getUserInfo() : UserInfoData
}