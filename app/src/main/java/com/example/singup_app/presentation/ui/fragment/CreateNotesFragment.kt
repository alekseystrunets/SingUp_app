package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.data.AppDatabase
import com.example.singup_app.presentation.ViewModels.CreateNoteFragmentViewModel
import com.example.singup_app.presentation.ViewModels.ViewModelFactory
import com.example.singup_app.presentation.action.CreateNoteFragmentAction

class CreateNotesFragment : Fragment() {

    private lateinit var headerEditText: AppCompatEditText
    private lateinit var dateEditText: AppCompatEditText
    private lateinit var messageEditText: AppCompatEditText
    private lateinit var createButton: AppCompatButton
    private lateinit var exitButton: AppCompatButton
    private lateinit var viewModel: CreateNoteFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_create_note, container, false)


        val database = AppDatabase.getDatabase(requireContext())
        val userNotesDao = database.userNotesDao()

        viewModel = ViewModelProvider(this, ViewModelFactory(userNotesDao)).get(CreateNoteFragmentViewModel::class.java)


        headerEditText = currentView.findViewById(R.id.header_cr_n)
        dateEditText = currentView.findViewById(R.id.date_cr_n)
        messageEditText = currentView.findViewById(R.id.message_cr_n)
        createButton = currentView.findViewById(R.id.create)
        exitButton = currentView.findViewById(R.id.exit)


        createButton.setOnClickListener {
            val header = headerEditText.text.toString()
            val date = dateEditText.text.toString()
            val message = messageEditText.text.toString()

            if (header.isNotEmpty() && date.isNotEmpty() && message.isNotEmpty()) {
                viewModel.createNote(header, date, message)
            } else {
                showToast("Заполните все поля")
            }
        }

        viewModel.action.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is CreateNoteFragmentAction.GoToBackAction -> back()
                CreateNoteFragmentAction.CreateNewNoteAction -> {
                    showToast("Заметка успешно создана")
                    back()
                }
            }
        })


        exitButton.setOnClickListener {
            viewModel.processAction(CreateNoteFragmentAction.GoToBackAction)
        }

        return currentView
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}