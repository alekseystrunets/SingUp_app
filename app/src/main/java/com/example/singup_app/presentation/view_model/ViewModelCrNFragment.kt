package com.example.singup_app.presentation.view_model

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelCrNFragment : ViewModel() {

    private val _liveData: MutableLiveData<Bundle?> = MutableLiveData(null)
    val liveData: LiveData<Bundle?> get() = _liveData

    fun checkFields(header: String, message: String, date: String) {
        if (header.isNotEmpty() || message.isNotEmpty() || date.isNotEmpty()) {
            val arguments = Bundle().apply {
                putString("header", header)
                putString("message", message)
                putString("date", date)
            }
            _liveData.value = arguments
        } else {
            _liveData.value = null
        }
    }
}