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
import com.example.singup_app.databinding.FragmentCreateNoteBinding
import com.example.singup_app.presentation.ViewModels.CreateNoteFragmentViewModel
import com.example.singup_app.presentation.action.CreateNoteFragmentAction

class CreateNotesFragment : Fragment() {

    private var _binding : FragmentCreateNoteBinding? = null
    private val binding : FragmentCreateNoteBinding get() = _binding!!

    private var viewModel: CreateNoteFragmentViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CreateNoteFragmentViewModel::class.java)

        // Обработчик нажатия кнопки "Create"
        binding.create.setOnClickListener {
            showProgressAndCreateNote() // Показать прогресс-бар
        }

        viewModel?.action?.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is CreateNoteFragmentAction.GoToBackAction -> back()
                CreateNoteFragmentAction.CreateNewNoteAction -> addNote()
            }
        })

        // Обработчик нажатия кнопки "Exit"
        binding.exit.setOnClickListener {
            viewModel?.processAction(CreateNoteFragmentAction.GoToBackAction)
        }

        return binding.root
    }

    private fun back() {
        val notesFragment = NotesFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, notesFragment) // Переход на NotesFragment
            .commit()
    }

    private fun addNote() {

    }

    private fun showProgressAndCreateNote() {
        // Создаем новый фрагмент для прогресса
        val progressFragment = PrFragment().apply {
            // Передаем данные во фрагмент, даже если они пустые
            arguments = Bundle().apply {
                putString("header", binding.headerCrN.text.toString())
                putString("date", binding.dateCrN.text.toString())
                putString("message", binding.messageCrN.text.toString())
            }
        }

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, progressFragment)
            .addToBackStack(null)
            .commit()

        // Уведомляем ViewModel о создании новой заметки
        viewModel?.processAction(CreateNoteFragmentAction.CreateNewNoteAction)
    }
}