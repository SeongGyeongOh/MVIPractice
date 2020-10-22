package com.osg.mvipractice.viewstate

import com.osg.mvipractice.data.User

sealed class MainState{
    object Idle: MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}