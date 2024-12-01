package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.presentation.ViewModels.CreateNoteFragmentViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction
import com.example.singup_app.presentation.action.NotesFragmentAction

class CreateNotesFragment : Fragment() {

    private var headerEditText: AppCompatEditText? = null
    private var dateEditText: AppCompatEditText? = null
    private var messageEditText: AppCompatEditText? = null
    private var createButton: AppCompatButton? = null
    private var exitButton: AppCompatButton? = null
    private var viewModel: CreateNoteFragmentViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_create_note, container, false)
        viewModel = ViewModelProvider(this).get(CreateNoteFragmentViewModel::class.java)

        // Инициализация полей ввода
        headerEditText = currentView.findViewById(R.id.header_cr_n)
        dateEditText = currentView.findViewById(R.id.date_cr_n)
        messageEditText = currentView.findViewById(R.id.message_cr_n)
        createButton = currentView.findViewById(R.id.create)
        exitButton = currentView.findViewById(R.id.exit)

        // Обработчик нажатия кнопки "Create"
        createButton?.setOnClickListener {
            viewModel?.processAction(CreateNoteFragmentAction.CreateNewNoteAction)
        }
        viewModel?.action?.observe(viewLifecycleOwner, Observer { action ->
            when(action) {
                is CreateNoteFragmentAction.GoToBackAction -> back()
                CreateNoteFragmentAction.CreateNewNoteAction -> addNote()
            }
        })
        // Обработчик нажатия кнопки "Exit"
        exitButton?.setOnClickListener {
            viewModel?.processAction(CreateNoteFragmentAction.GoToBackAction)
        }

        return currentView
    }
    private fun back(){
        parentFragmentManager.popBackStack()
    }
    private fun addNote(){
        val header = headerEditText?.text.toString()
        val date = dateEditText?.text.toString()
        val message = messageEditText?.text.toString()

        // Проверка на пустые поля (опционально)
        if (header.isNotEmpty() && date.isNotEmpty() && message.isNotEmpty()) {
            // Передаем данные во фрагмент NotesFragment
            val noteBundle = Bundle().apply {
                putString("header", header)
                putString("date", date)
                putString("message", message)
            }

            val notesFragment = NotesFragment()
            notesFragment.arguments = noteBundle

            // Переходим на NotesFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_main, notesFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
