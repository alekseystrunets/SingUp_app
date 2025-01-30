package com.example.singup_app.presentation.view.actions

sealed class ViewCrNFragmetAction {
    object CheckingForEmptyHeader: ViewCrNFragmetAction()

    object CheckingForEmptyDate: ViewCrNFragmetAction()

    object CheckingForEmptyMessage: ViewCrNFragmetAction()

    object AllFealdsIsNotEmpty: ViewCrNFragmetAction()
}

