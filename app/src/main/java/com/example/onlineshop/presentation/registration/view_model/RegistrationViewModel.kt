package com.example.onlineshop.presentation.registration.view_model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.domain.models.User
import com.example.onlineshop.domain.registration.RegistrationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationInteractor: RegistrationInteractor
) : ViewModel() {

    private val _isAllFieldsValid = MutableLiveData(false)
    val isAllFieldsValid: LiveData<Boolean> = _isAllFieldsValid


    fun saveUserCredentials(
        firstName: Editable?,
        lastName: Editable?,
        phoneNumber: Editable?
    ){
        viewModelScope.launch(Dispatchers.IO) {

            val userData = User(
                firstName = firstName.toString(),
                lastName = lastName.toString(),
                phoneNumber = phoneNumber.toString()
            )
            registrationInteractor.saveUserData(userData)
        }
    }

    fun isTextInputValid(text: Editable?): Boolean {
        val words = ('а'..'я') + ('А'..'Я')

        if (text.toString().isEmpty()) {
            return true
        }
        text.toString().forEach {
            if (!words.contains(it)) {
                return false
            }
        }
        return true
    }


    fun isPhoneInputValid(text: Editable?): Boolean {
        if (text.toString().isEmpty()) {
            return true
        }
        return text.toString().length >= PHONE_SYMBOLS_NUMBER
    }

    fun isAllInputsValid(
        firstName: Editable?,
        lastName: Editable?,
        phoneNumber: Editable?
    ){
        _isAllFieldsValid.value = (isTextInputValid(firstName) && isTextInputValid(lastName)
                && isPhoneInputValid(phoneNumber) && firstName.toString()
            .isNotBlank() && lastName.toString()
            .isNotBlank() && phoneNumber.toString().isNotBlank())
    }

    companion object{
        const val PHONE_SYMBOLS_NUMBER = 18
    }
}