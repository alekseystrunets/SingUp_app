package com.example.singup_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Notes : AppCompatActivity() {
    private val listOfNotesUsers = mutableListOf(
        UserNotes("First", "first message", "01.05.2024"),
        UserNotes("Second", "second message", "02.05.2024"),
        UserNotes("Third", "third message", "03.05.2024")
    )

    private lateinit var adapter: Logic_myNotes_adapter
    private lateinit var createNoteLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        adapter = Logic_myNotes_adapter(listOfNotesUsers) { position ->
            deleteNoteAt(position)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        createNoteLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let { data ->
                    val header = data.getStringExtra("header") ?: ""
                    val date = data.getStringExtra("date") ?: ""
                    val message = data.getStringExtra("message") ?: ""


                    listOfNotesUsers.add(UserNotes(header, message, date))
                    adapter.notifyItemInserted(listOfNotesUsers.size - 1)
                }
            }
        }


        findViewById<Button>(R.id.add_note_button).setOnClickListener {
            val intent = Intent(this, Create_notes::class.java)
            createNoteLauncher.launch(intent)
        }
    }

    private fun deleteNoteAt(position: Int) {
        listOfNotesUsers.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    data class UserNotes(
        val header: String,
        val message: String,
        val date: String
    )
}