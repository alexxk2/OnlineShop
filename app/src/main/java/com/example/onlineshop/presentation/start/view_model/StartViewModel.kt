package com.example.onlineshop.presentation.start.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.registration.RegistrationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
) : ViewModel() {

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    fun getLoginStatus(){
        viewModelScope.launch(Dispatchers.IO) {
            delay(PRETENDING_LOADING_DELAY)
            _isUserLoggedIn.postValue(registrationInteractor.isUseLoggedIn())
        }
    }

    companion object{
        const val PRETENDING_LOADING_DELAY = 1000L
    }

}