package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.presentation.ViewModels.CreateNoteFragmentViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction
import com.example.singup_app.presentation.action.NotesFragmentAction

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
        viewModel = ViewModelProvider(this).get(CreateNoteFragmentViewModel::class.java)

        // Инициализация полей ввода
        headerEditText = currentView.findViewById(R.id.header_cr_n)
        dateEditText = currentView.findViewById(R.id.date_cr_n)
        messageEditText = currentView.findViewById(R.id.message_cr_n)
        createButton = currentView.findViewById(R.id.create)
        exitButton = currentView.findViewById(R.id.exit)

        // Наблюдение за состоянием
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            headerEditText.setText(state.header)
            dateEditText.setText(state.date)
            messageEditText.setText(state.message)
        })

        // Обработчики нажатий
        createButton.setOnClickListener {
            viewModel.processAction(CreateNoteFragmentAction.CreateNewNoteAction)
        }

        exitButton.setOnClickListener {
            viewModel.processAction(CreateNoteFragmentAction.GoToBackAction)
        }

        // Обработка изменений в полях ввода
        headerEditText.addTextChangedListener { text ->
            viewModel.processAction(CreateNoteFragmentAction.UpdateHeader(text.toString()))
        }

        dateEditText.addTextChangedListener { text ->
            viewModel.processAction(CreateNoteFragmentAction.UpdateDate(text.toString()))
        }

        messageEditText.addTextChangedListener { text ->
            viewModel.processAction(CreateNoteFragmentAction.UpdateMessage(text.toString()))
        }

        // Наблюдение за действиями
        viewModel.action.observe(viewLifecycleOwner, Observer { action ->
            when(action) {
                is CreateNoteFragmentAction.GoToBackAction -> back()
                CreateNoteFragmentAction.CreateNewNoteAction -> addNote()
                is CreateNoteFragmentAction.UpdateDate -> TODO()
                is CreateNoteFragmentAction.UpdateHeader -> TODO()
                is CreateNoteFragmentAction.UpdateMessage -> TODO()
            }
        })

        return currentView
    }

    private fun back() {
        parentFragmentManager.popBackStack()
    }

    private fun addNote() {
        val currentState = viewModel.state.value ?: return
        // Проверка на пустые поля (опционально)
        if (currentState.header.isNotEmpty() && currentState.date.isNotEmpty() && currentState.message.isNotEmpty()) {
            // Передаем данные во фрагмент NotesFragment
            val noteBundle = Bundle().apply {
                putString("header", currentState.header)
                putString("date", currentState.date)
                putString("message", currentState.message)
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
