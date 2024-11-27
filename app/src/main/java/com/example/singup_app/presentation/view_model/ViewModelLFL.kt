package com.example.singup_app.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.view.actions.ViewLFLAction

class ViewModelLFL : ViewModel() {
    private val liveData: MutableLiveData<ViewLFLAction> = MutableLiveData()
    val publicLiveData: LiveData<ViewLFLAction> = liveData

    fun validateInputs(email: String, login: String, password: String) {
        when {
            email.isEmpty() || login.isEmpty() || password.isEmpty() -> {
                liveData.value = ViewLFLAction.ShowToast("Не все поля заполнены")
            }
            !isValidEmail(email) -> {
                liveData.value = ViewLFLAction.ShowToast("Некорректный адрес электронной почты")
            }
            password.length < 8 -> {
                liveData.value = ViewLFLAction.ShowToast("Пароль должен содержать не менее 8 символов")
            }
            else -> {
                liveData.value = ViewLFLAction.ValidationSuccess
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}