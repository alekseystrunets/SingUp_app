package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.presentation.ViewModels.UserAccountViewModel
import com.example.singup_app.presentation.action.UserAccountFragmentAction

class UserAccountFragment : Fragment() {

    private lateinit var userEmailEditText: EditText
    private lateinit var userLoginEditText: EditText
    private lateinit var buttonToNotes: Button
    private lateinit var viewModel: UserAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_account_user, container, false)

        userEmailEditText = currentView.findViewById(R.id.user_account_email1)
        userLoginEditText = currentView.findViewById(R.id.user_account_login)
        buttonToNotes = currentView.findViewById(R.id.button_to_notes)

        viewModel = ViewModelProvider(this).get(UserAccountViewModel::class.java)


        viewModel.email.observe(viewLifecycleOwner, Observer {
            userEmailEditText.setText(it)
        })

        viewModel.login.observe(viewLifecycleOwner, Observer {
            userLoginEditText.setText(it)
        })

        // Получаем данные из arguments
        val email = arguments?.getString("email") ?: ""
        val login = arguments?.getString("login") ?: ""


        viewModel.setEmail(email)
        viewModel.setLogin(login)

        viewModel.action.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is UserAccountFragmentAction.GoToTheNotePageAction -> openUserNotes()
            }
        })

        buttonToNotes.setOnClickListener {
            viewModel.processAction(UserAccountFragmentAction.GoToTheNotePageAction)
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