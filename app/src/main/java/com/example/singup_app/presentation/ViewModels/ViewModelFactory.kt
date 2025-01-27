package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.data.db_room.UserNotesDao

class ViewModelFactory(private val userNotesDao: UserNotesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateNoteFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateNoteFragmentViewModel(userNotesDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}