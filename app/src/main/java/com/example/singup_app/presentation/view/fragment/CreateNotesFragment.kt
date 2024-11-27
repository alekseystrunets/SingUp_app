package com.example.singup_app.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.presentation.view.actions.ViewCrNFragmetAction
import com.example.singup_app.presentation.view_model.ViewModelCrNFragment

open class CreateNotesFragment : Fragment() {

    private var viewModel: ViewModelCrNFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(ViewModelCrNFragment::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.publicLiveData?.observe(viewLifecycleOwner) { action ->
            val header = headerEditText?.text.toString()
            val date = dateEditText?.text.toString()
            val message = messageEditText?.text.toString()

            when (action) {
                is ViewCrNFragmetAction.checkingForEmptyFields -> {
                    val allFieldsFilled = header.isNotEmpty() && date.isNotEmpty() && message.isNotEmpty()

                    if (allFieldsFilled) {

                        val noteBundle = Bundle().apply {
                            putString("header", header)
                            putString("date", date)
                            putString("message", message)
                        }


                        val notesFragment = NotesFragment().apply {
                            arguments = noteBundle
                        }

                        parentFragmentManager.beginTransaction()
                            .replace(R.id.main, notesFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }
        }
    }
    private var headerEditText: AppCompatEditText? = null
    private var dateEditText: AppCompatEditText? = null
    private var messageEditText: AppCompatEditText? = null
    private var createButton: AppCompatButton? = null
    private var exitButton: AppCompatButton? = null

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

            viewModel?.validate(header, date, message) // Вызываем метод в ViewModel
        }

        // Обработчик нажатия кнопки "Exit"
        exitButton?.setOnClickListener {
            // Просто возвращаемся на предыдущий экран
            parentFragmentManager.popBackStack()
        }

        return currentView
    }
}
