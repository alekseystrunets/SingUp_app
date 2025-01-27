package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.singup_app.data.db_room.UserNotes
import com.example.singup_app.data.db_room.UserNotesDao
import com.example.singup_app.presentation.action.CreateNoteFragmentAction
import kotlinx.coroutines.launch

class CreateNoteFragmentViewModel(private val userNotesDao: UserNotesDao) : ViewModel() {


    private val _action = MutableLiveData<CreateNoteFragmentAction>()
    val action: LiveData<CreateNoteFragmentAction> get() = _action

    fun createNote(header: String, date: String, message: String) {
        val note = UserNotes(header = header, message = message, date = date)

        viewModelScope.launch {
            userNotesDao.insert(note)
            _action.postValue(CreateNoteFragmentAction.CreateNewNoteAction)
        }
    }

    fun processAction(action: CreateNoteFragmentAction) {
        _action.value = action
    }
}