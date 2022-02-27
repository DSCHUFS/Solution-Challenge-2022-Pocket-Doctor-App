package com.hot.pocketdoctor.util

object SubjectUtils {
    fun convertSubjectWithHashTag(subject: String): String {
        val subjectHashTag = StringBuilder()
        val subjectList = subject.split(",")
        subjectList.forEach {
            subjectHashTag.append("#$it ")
        }
        return subjectHashTag.toString()
    }
}