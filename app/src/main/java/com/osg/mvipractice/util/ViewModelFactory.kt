package com.osg.mvipractice.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.osg.mvipractice.data.ApiHelper
import com.osg.mvipractice.data.MainRepository
import com.osg.mvipractice.viewstate.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(p0: Class<T>): T {
        if(p0.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}