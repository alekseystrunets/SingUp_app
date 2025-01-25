package com.example.singup_app.presentation.action

sealed class CreateNoteFragmentAction {

    data object GoToBackAction: CreateNoteFragmentAction()

    data object CreateNewNoteAction : CreateNoteFragmentAction()

    data class UpdateHeader(val header: String) : CreateNoteFragmentAction()

    data class UpdateDate(val date: String) : CreateNoteFragmentAction()

    data class UpdateMessage(val message: String) : CreateNoteFragmentAction()
}