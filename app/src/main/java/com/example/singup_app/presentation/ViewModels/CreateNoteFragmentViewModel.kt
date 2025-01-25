package com.example.singup_app.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction

class CreateNoteFragmentViewModel: ViewModel() {
private val _action = MutableLiveData<CreateNoteFragmentAction>()
    val action : LiveData<CreateNoteFragmentAction> get() = _action

    fun processAction(action: CreateNoteFragmentAction){
        _action.value = action
    }
}