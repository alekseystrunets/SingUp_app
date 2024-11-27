package com.example.singup_app.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.singup_app.presentation.view.actions.ViewCrNFragmetAction

class ViewModelCrNFragment: ViewModel() {
    val liveData: MutableLiveData<ViewCrNFragmetAction> = MutableLiveData(null)
    val publicLiveData: LiveData<ViewCrNFragmetAction> = liveData
    fun validate(header: String, date: String, message: String) {
        if (header.isNotEmpty() && date.isNotEmpty() && message.isNotEmpty()) {
            liveData.value = ViewCrNFragmetAction.checkingForEmptyFields
        }
    }
}