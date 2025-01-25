package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.action.UserAccountFragmentAction

class UserAccountViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _login = MutableLiveData<String>()
    val login: LiveData<String> get() = _login

    private val _action = MutableLiveData<UserAccountFragmentAction>()
    val action: LiveData<UserAccountFragmentAction> get() = _action

    fun processAction(action: UserAccountFragmentAction) {
        _action.value = action
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setLogin(login: String) {
        _login.value = login
    }
}