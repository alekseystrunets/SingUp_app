package com.example.singup_app.presentation.view.actions

sealed class ViewLFLAction {
    data class ShowToast(val message: String) : ViewLFLAction() // Для показа тостов
    object ValidationSuccess : ViewLFLAction() // Для успешной валидации
}