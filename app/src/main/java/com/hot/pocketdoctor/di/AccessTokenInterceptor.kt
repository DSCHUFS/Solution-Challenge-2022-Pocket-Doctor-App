package com.hot.pocketdoctor.di

import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference.ACCESS_TOKEN_HEADER
import com.hot.pocketdoctor.data.preference.PocketDoctorSharedPreference.preferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = PocketDoctorSharedPreference.getUserToken()

        if (jwtToken != null) {
            builder.addHeader(ACCESS_TOKEN_HEADER, "Bearer $jwtToken")
        }

        return chain.proceed(builder.build())

    }
}