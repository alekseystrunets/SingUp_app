package com.example.singup_app.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.NotesFragment
import com.example.singup_app.R
import com.example.singup_app.presentation.view_model.ViewModelCrNFragment
import com.example.singup_app.presentation.view_model.ViewModelLFL

class CreateNotesFragment : Fragment() {

    private lateinit var viewModel: ViewModelCrNFragment
    private lateinit var headerEditText: AppCompatEditText
    private lateinit var dateEditText: AppCompatEditText
    private lateinit var messageEditText: AppCompatEditText
    private lateinit var createButton: AppCompatButton
    private lateinit var exitButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelCrNFragment::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_create_note, container, false)

        headerEditText = currentView.findViewById(R.id.header_cr_n)
        dateEditText = currentView.findViewById(R.id.date_cr_n)
        messageEditText = currentView.findViewById(R.id.message_cr_n)
        createButton = currentView.findViewById(R.id.create)
        exitButton = currentView.findViewById(R.id.exit)

        createButton.setOnClickListener {
            // Получаем актуальные значения из полей ввода только при нажатии на кнопку
            val header = headerEditText.text.toString()
            val date = dateEditText.text.toString()
            val message = messageEditText.text.toString()
            viewModel.checkFields(header, message, date)
            observeViewModel()
        }

        exitButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun toNextScreen(newData: Bundle) {
        val notesFragment = NotesFragment().apply { arguments = newData }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, notesFragment)
            .addToBackStack(null)
            .commit()
    }
    fun observeViewModel(){
        viewModel.liveData.observe(viewLifecycleOwner){  newData ->
            if (newData != null) {
                toNextScreen(newData)
            } else {
                Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            }
        }
    }
}