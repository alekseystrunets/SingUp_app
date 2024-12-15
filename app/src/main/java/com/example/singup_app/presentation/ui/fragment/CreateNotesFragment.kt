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
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.singup_app.R
import com.example.singup_app.presentation.ViewModels.CreateNoteFragmentViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction

class CreateNotesFragment : Fragment() {

    private var headerEditText: AppCompatEditText? = null
    private var dateEditText: AppCompatEditText? = null
    private var messageEditText: AppCompatEditText? = null
    private var createButton: AppCompatButton? = null
    private var exitButton: AppCompatButton? = null
    private val viewModel: CreateNoteFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_create_note, container, false)

        // Инициализация полей ввода
        headerEditText = currentView.findViewById(R.id.header_cr_n)
        dateEditText = currentView.findViewById(R.id.date_cr_n)
        messageEditText = currentView.findViewById(R.id.message_cr_n)
        createButton = currentView.findViewById(R.id.create)
        exitButton = currentView.findViewById(R.id.exit)

        // Обработчик нажатия кнопки "Create"
        createButton?.setOnClickListener {
            val header = headerEditText?.text.toString()
            val date = dateEditText?.text.toString()
            val message = messageEditText?.text.toString()
            viewModel.setInputData(header, date, message)
            viewModel.processAction(CreateNoteFragmentAction.CreateNewNoteAction)
        }

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when {
                state.create -> addNote()
                state.back -> back()
                state.errorMessage != null -> Toast.makeText(requireContext(), state.errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

        // Обработчик нажатия кнопки "Exit"
        exitButton?.setOnClickListener {
            viewModel.processAction(CreateNoteFragmentAction.GoToBackAction)
        }

        return currentView
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }

    private fun addNote() {
        val header = headerEditText?.text.toString()
        val date = dateEditText?.text.toString()
        val message = messageEditText?.text.toString()

        val noteBundle = Bundle().apply {
            putString("header", header)
            putString("date", date)
            putString("message", message)
        }

        val notesFragment = NotesFragment().apply {
            arguments = noteBundle
        }

        // Переходим на NotesFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, notesFragment)
            .addToBackStack(null)
            .commit()
    }
}