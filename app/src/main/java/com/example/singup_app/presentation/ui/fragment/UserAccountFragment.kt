package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.singup_app.R
import com.example.singup_app.presentation.ViewModels.UserAccountViewModel
import com.example.singup_app.presentation.action.UserAccountFragmentAction

class UserAccountFragment : Fragment() {

    private var userEmailTextView: TextView? = null
    private var userLoginTextView: TextView? = null
    private var buttonToNotes: Button? = null
    private var viewModel: UserAccountViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val currentView = inflater.inflate(R.layout.fragment_account_user, container, false)

        userEmailTextView = currentView.findViewById(R.id.user_account_email1)
        userLoginTextView = currentView.findViewById(R.id.user_account_login)
        buttonToNotes = currentView.findViewById(R.id.button_to_notes)

        viewModel = ViewModelProvider(this).get(UserAccountViewModel::class.java)

        // Получаем данные из arguments
        val email = arguments?.getString("email")
        val login = arguments?.getString("login")

        // Устанавливаем полученные данные в TextView
        userEmailTextView?.text = email
        userLoginTextView?.text = login

        viewModel?.action?.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                is UserAccountFragmentAction.GoToTheNotePageAction -> openUserNotes()
            }
        })

        buttonToNotes?.setOnClickListener {
            viewModel?.processAction(UserAccountFragmentAction.GoToTheNotePageAction)
        }

        return currentView
    }

    private fun openUserNotes() {
        findNavController().navigate(R.id.action_userAccountFragment_to_notesFragment)
    }
}