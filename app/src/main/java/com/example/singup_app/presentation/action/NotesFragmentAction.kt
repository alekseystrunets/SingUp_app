package com.example.singup_app.presentation.action

import com.example.singup_app.UserNotes

sealed class NotesFragmentAction {
    object GoToAddNotePageAction : NotesFragmentAction()
    data class AddNoteAction(val note: UserNotes) : NotesFragmentAction()
    data class DeleteNoteAction(val position: Int) : NotesFragmentAction()
}