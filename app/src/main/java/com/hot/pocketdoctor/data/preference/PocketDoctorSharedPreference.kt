package com.hot.pocketdoctor.data.preference

import android.content.Context
import android.content.SharedPreferences

object PocketDoctorSharedPreference {

    const val ACCESS_TOKEN_HEADER = "Authorization"
    private const val USER_TOKEN = "USER_TOKEN"
    private const val PREFERENCE_NAME = "POCKET_DOCTOR"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun getUserToken() = preferences.getString(USER_TOKEN, "Bearer ") ?: "Bearer "

    fun setUserToken(accessToken: String) = preferences.edit()
        .putString(USER_TOKEN, accessToken).apply()
}