package com.osg.mvipractice.viewstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.osg.mvipractice.data.MainRepository
import com.osg.mvipractice.intent.MainIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlin.Exception


@ExperimentalCoroutinesApi
class MainViewModel (private val repository: MainRepository): ViewModel(){
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableLiveData<MainState>()
    val state : LiveData<MainState> get()= _state
    init {
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when(it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try{
                MainState.Users(repository.getUsers())
            }catch (e:Exception){
                MainState.Error(e.localizedMessage)
            }
        }
    }
}