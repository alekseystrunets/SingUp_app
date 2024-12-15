package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction
import com.example.singup_app.presentation.action.LoginFragmentAction

class LoginFragmentViewModel : ViewModel() {
    private val _state = MutableLiveData<LoginInputs>()
    val state: LiveData<LoginInputs> get() = _state
    fun processAction(action: LoginFragmentAction) = when (action) {
        LoginFragmentAction.GoToNextScreen -> {
            _state.value.let { currentState ->
                if ((currentState?.email?.isNotEmpty() == true) &&
                    (currentState.login?.isNotEmpty() == true) &&
                    (currentState.password?.isNotEmpty() == true)
                ) {
                    _state.value = LoginInputs(nextScreen = true)
                }
                else{
                    _state.value = LoginInputs(errorMessage = "Пожалуйста, заполните все поля")
                }
            }
        }
    }

    data class LoginInputs(
        val email: String? = null,
        val login: String? = null,
        val password: String? = null,
        val errorMessage: String? = null,
        val nextScreen: Boolean = false
    )
}