package com.example.onlineshop.presentation.profile.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.models.User
import com.example.onlineshop.domain.profile.ProfileInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileInteractor: ProfileInteractor
): ViewModel() {

    private val _usersData = MutableLiveData<User>()
    val userData: LiveData<User> = _usersData

    private val _favoritesNumber = MutableLiveData<Int>()
    val favoritesNumber: LiveData<Int> = _favoritesNumber


    fun getUsersData(){
        viewModelScope.launch(Dispatchers.IO) {
           _usersData.postValue(profileInteractor.getUserInfo())
        }
    }

    fun clearAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            profileInteractor.clearAllData()
        }
    }

    fun getFavoritesNumber(){
        viewModelScope.launch(Dispatchers.IO) {
            _favoritesNumber.postValue(profileInteractor.getFavoritesNumber())
        }
    }

}