package com.osg.mvipractice.data

//데이터를 요청하는 repository
class MainRepository (private val apiHelper: ApiHelper){
    suspend fun getUsers()=apiHelper.getUsers()
}