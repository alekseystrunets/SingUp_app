package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.UserNotes
import com.example.singup_app.presentation.action.NotesFragmentAction

class NotesFragmentViewModel : ViewModel() {
    private val _action = MutableLiveData<NotesFragmentAction>()
    val action: LiveData<NotesFragmentAction> get() = _action

    private val _notesList = MutableLiveData<List<UserNotes>>(emptyList())
    val notesList: LiveData<List<UserNotes>> get() = _notesList

    fun processAction(action: NotesFragmentAction) {
        _action.value = action
        when (action) {
            is NotesFragmentAction.AddNoteAction -> addNote(action.note)
            is NotesFragmentAction.DeleteNoteAction -> deleteNote(action.position)
            NotesFragmentAction.GoToAddNotePageAction -> TODO()
        }
    }

    private fun addNote(note: UserNotes) {
        val updatedList = _notesList.value?.toMutableList() ?: mutableListOf()
        updatedList.add(note)
        _notesList.value = updatedList
    }

    private fun deleteNote(position: Int) {
        val updatedList = _notesList.value?.toMutableList() ?: mutableListOf()
        if (position in updatedList.indices) {
            updatedList.removeAt(position)
            _notesList.value = updatedList
        }
    }
}