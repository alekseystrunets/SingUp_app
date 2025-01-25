package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.singup_app.R
import com.example.singup_app.UserNotes
import com.example.singup_app.presentation.ViewModels.NotesFragmentViewModel
import com.example.singup_app.presentation.action.NotesFragmentAction

class NotesFragment : Fragment() {

    private val listOfNotesUsers = mutableListOf<UserNotes>()
    private var adapter: LogicMyNotesAdapter? = null
    private var addNoteButton: Button? = null
    private var viewModel: NotesFragmentViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_note, container, false)

        addNoteButton = currentView.findViewById(R.id.add_note_button)
        viewModel = ViewModelProvider(this).get(NotesFragmentViewModel::class.java)

        viewModel?.action?.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is NotesFragmentAction.GoToAddNotePageAction -> toNextScreen()
            }
        })

        // Обработчик нажатия кнопки "Add Note"
        addNoteButton?.setOnClickListener {
            viewModel?.processAction(NotesFragmentAction.GoToAddNotePageAction)
        }

        // Получаем переданные данные (если есть)
        arguments?.let {
            val header = it.getString("header", "")
            val date = it.getString("date", "")
            val message = it.getString("message", "")

            // Если данные были переданы, добавляем заметку в список
            if (header.isNotEmpty()) {
                listOfNotesUsers.add(UserNotes(header, message, date))
            }
        }

        // Настройка RecyclerView
        val recyclerView = currentView.findViewById<RecyclerView>(R.id.recycle_view)
        adapter = LogicMyNotesAdapter(listOfNotesUsers) { position -> deleteNoteAt(position) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return currentView
    }

    private fun deleteNoteAt(position: Int) {
        listOfNotesUsers.removeAt(position)
        adapter?.notifyItemRemoved(position)
    }

    private fun toNextScreen() {
        val createNotesFragment = CreateNotesFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, createNotesFragment)
            .addToBackStack(null) // Добавляем в back stack, чтобы можно было вернуться назад
            .commit()
    }
}