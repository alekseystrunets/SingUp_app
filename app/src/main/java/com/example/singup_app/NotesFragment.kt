package com.example.singup_app

import android.content.Context
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.singup_app.Notes.UserNotes

class NotesFragment : Fragment() {
    private val listOfNotesUsers = mutableListOf(
        UserNotes("First", "first message", "01.05.2024"),
        UserNotes("Second", "second message", "02.05.2024"),
        UserNotes("Third", "third message", "03.05.2024")
    )

    private var adapter: LogicMyNotesAdapter? = null
    private var createNoteLauncher: ActivityResultLauncher<Intent>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { data ->
                    val header = data.getStringExtra("header") ?: ""
                    val date = data.getStringExtra("date") ?: ""
                    val message = data.getStringExtra("message") ?: ""

                    listOfNotesUsers.add(UserNotes(header, message, date))
                    adapter?.notifyItemInserted(listOfNotesUsers.size - 1)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_note, container, false)

        val recyclerView = currentView.findViewById<RecyclerView>(R.id.recycle_view)
        adapter = LogicMyNotesAdapter(listOfNotesUsers) { position ->
            deleteNoteAt(position)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        currentView.findViewById<Button>(R.id.add_note_button).setOnClickListener {
            val intent = Intent(context, Create_notes::class.java)
            createNoteLauncher?.launch(intent)
        }

        return currentView
    }

    private fun deleteNoteAt(position: Int) {
        listOfNotesUsers.removeAt(position)
        adapter?.notifyItemRemoved(position)
    }
}