package com.example.singup_app.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.view.actions.ViewLFLAction

class ViewModelLFL : ViewModel() {
    private val _liveData: MutableLiveData<Bundle?> = MutableLiveData()
    val publicLiveData: LiveData<Bundle?> get() = _liveData

    fun validateInputs(email: String, login: String, password: String) {
        when {
            email.isEmpty() || login.isEmpty() || password.isEmpty() -> {
                _liveData.value = null
            }
            !isValidEmail(email) -> {
                _liveData.value = null
            }
            password.length < 8 -> {
                _liveData.value = null
            }
            else -> {
                val arguments = Bundle().apply {
                    putString("email", email)
                    putString("login", login)
                }
                _liveData.value = arguments
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}