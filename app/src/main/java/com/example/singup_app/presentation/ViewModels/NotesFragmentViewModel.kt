package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.action.NotesFragmentAction


class NotesFragmentViewModel:ViewModel() {
    private val _action = MutableLiveData<NotesFragmentAction>()
    val action : LiveData<NotesFragmentAction> get() = _action

    fun processAction(action: NotesFragmentAction){
        _action.value=action
    }
}