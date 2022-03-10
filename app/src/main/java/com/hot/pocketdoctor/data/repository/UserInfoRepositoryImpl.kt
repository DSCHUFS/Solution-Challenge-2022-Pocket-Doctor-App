package com.hot.pocketdoctor.data.repository

import com.hot.pocketdoctor.data.datasource.user.UserInfoDataSource
import com.hot.pocketdoctor.data.mapper.UserAuthMapper
import com.hot.pocketdoctor.domain.model.UserInfoData
import com.hot.pocketdoctor.domain.repository.UserInfoRepository

class UserInfoRepositoryImpl(private val userInfoDataSource: UserInfoDataSource) : UserInfoRepository {
    override suspend fun getUserInfo(): UserInfoData {
        return UserAuthMapper.mapperToUserInfoData(userInfoDataSource.getUserInfo())
    }
}