package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction

class CreateNoteFragmentViewModel : ViewModel() {
    private val _state = MutableLiveData<CreateNoteState>()
    val state: LiveData<CreateNoteState> get() = _state

    fun processAction(action: CreateNoteFragmentAction) {
        when (action) {
            CreateNoteFragmentAction.CreateNewNoteAction -> {
                // Валидация введенных данных
                _state.value?.let { currentState ->
                    if (currentState.header.isNotEmpty() && currentState.date.isNotEmpty() && currentState.message.isNotEmpty()) {
                        _state.value = CreateNoteState(create = true)
                    } else {
                        // Уведомление о неверных данных
                        _state.value = CreateNoteState(errorMessage = "Пожалуйста, заполните все поля")
                    }
                }
            }
            CreateNoteFragmentAction.GoToBackAction -> _state.value = _state.value?.copy(back = true)
        }
    }

    fun setInputData(header: String, date: String, message: String) {
        _state.value = CreateNoteState(header = header, date = date, message = message)
    }
}

data class CreateNoteState(
    val create: Boolean = false,
    val back: Boolean = false,
    val header: String = "",
    val date: String = "",
    val message: String = "",
    val errorMessage: String? = null
)