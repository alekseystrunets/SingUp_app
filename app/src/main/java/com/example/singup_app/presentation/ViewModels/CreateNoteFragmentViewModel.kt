package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.CreateNoteState
import com.example.singup_app.UserNotes
import com.example.singup_app.presentation.action.CreateNoteFragmentAction

class CreateNoteFragmentViewModel : ViewModel() {
    private val _state = MutableLiveData<CreateNoteState>()
    val state: LiveData<CreateNoteState> get() = _state

    private val _action = MutableLiveData<CreateNoteFragmentAction>()
    val action: LiveData<CreateNoteFragmentAction> get() = _action

    init {
        _state.value = CreateNoteState()
    }

    fun processAction(action: CreateNoteFragmentAction) {
        when (action) {
            is CreateNoteFragmentAction.CreateNewNoteAction -> {
                val currentState = _state.value ?: return
                val newNote = UserNotes(
                    header = currentState.header,
                    message = currentState.message,
                    date = currentState.date
                )
                val updatedNotes = currentState.userNotes + newNote
                _state.value = currentState.copy(userNotes = updatedNotes)
                _action.value = action
            }
            is CreateNoteFragmentAction.UpdateHeader -> {
                _state.value = _state.value?.copy(header = action.header)
            }
            is CreateNoteFragmentAction.UpdateDate -> {
                _state.value = _state.value?.copy(date = action.date)
            }
            is CreateNoteFragmentAction.UpdateMessage -> {
                _state.value = _state.value?.copy(message = action.message)
            }
            is CreateNoteFragmentAction.GoToBackAction -> {
                _action.value = action
            }
        }
    }
}