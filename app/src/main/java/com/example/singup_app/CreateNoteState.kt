package com.example.singup_app

data class CreateNoteState(
     val header: String = "",
     val date: String = "",
     val message: String = "",
     val userNotes: List<UserNotes> = emptyList()
 )

