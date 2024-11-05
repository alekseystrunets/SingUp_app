package com.example.singup_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserAccountFragment : Fragment() {

    private lateinit var userEmailTextView: TextView
    private lateinit var userLoginTextView: TextView
    private lateinit var buttonToNotes: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val currentView = inflater.inflate(R.layout.fragment_account_user, container, false)

        userEmailTextView = currentView.findViewById(R.id.user_account_email1)
        userLoginTextView = currentView.findViewById(R.id.user_account_login)
        buttonToNotes = currentView.findViewById(R.id.button_to_notes)

        // Получаем данные из arguments
        val email = arguments?.getString("email")
        val login = arguments?.getString("login")

        // Устанавливаем полученные данные в TextView
        userEmailTextView.text = email
        userLoginTextView.text = login

        buttonToNotes.setOnClickListener {
            openUserNotes()
        }

        return currentView
    }

    private fun openUserNotes() {
        val fragment = NotesFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}
