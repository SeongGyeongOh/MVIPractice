package com.osg.mvipractice.data

interface ApiHelper {
    suspend fun getUsers(): List<User>
}