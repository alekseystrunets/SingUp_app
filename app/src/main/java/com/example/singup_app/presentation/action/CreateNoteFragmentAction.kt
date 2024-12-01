package com.example.singup_app.presentation.action

sealed class CreateNoteFragmentAction {

    data object GoToBackAction: CreateNoteFragmentAction()

    data object CreateNewNoteAction : CreateNoteFragmentAction()

}