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

    private lateinit var adapter: LogicMyNotesAdapter
    private lateinit var viewModel: NotesFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_note, container, false)

        val addNoteButton = currentView.findViewById<Button>(R.id.add_note_button)
        viewModel = ViewModelProvider(this).get(NotesFragmentViewModel::class.java)


        val recyclerView = currentView.findViewById<RecyclerView>(R.id.recycle_view)
        adapter = LogicMyNotesAdapter(emptyList()) { position ->
            viewModel.processAction(NotesFragmentAction.DeleteNoteAction(position))
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Наблюдение за изменениями в списке заметок
        viewModel.notesList.observe(viewLifecycleOwner, Observer { notes ->
            adapter.updateNotes(notes)
        })


        addNoteButton.setOnClickListener {
            val header = "Header"
            val date = "Date"
            val message = "Message"
            val newNote = UserNotes(header, message, date)
            viewModel.processAction(NotesFragmentAction.AddNoteAction(newNote))
            toNextScreen()
        }


        arguments?.let {
            val header = it.getString("header", "")
            val date = it.getString("date", "")
            val message = it.getString("message", "")
            if (header.isNotEmpty()) {
                viewModel.processAction(NotesFragmentAction.AddNoteAction(UserNotes(header, message, date)))
            }
        }

        return currentView
    }

    private fun toNextScreen() {
        val createNotesFragment = CreateNotesFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, createNotesFragment)
            .addToBackStack(null)
            .commit()
    }
}