package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.databinding.FragmentAccountUserBinding
import com.example.singup_app.presentation.ViewModels.UserAccountViewModel
import com.example.singup_app.presentation.action.UserAccountFragmentAction

class UserAccountFragment : Fragment() {

    private var _binding: FragmentAccountUserBinding? = null
    private val binding : FragmentAccountUserBinding get() = _binding!!

    private  var userEmailTextView: TextView? = null
    private  var userLoginTextView: TextView? = null
    private  var buttonToNotes: Button? = null
    private var viewModel : UserAccountViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAccountUserBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(UserAccountViewModel::class.java)

        // Получаем данные из arguments
        val email = arguments?.getString("email")
        val login = arguments?.getString("login")

        // Устанавливаем полученные данные в TextView
        binding.userAccountEmail1.text = email
        binding.userAccountLogin.text = login

        viewModel?.action?.observe(viewLifecycleOwner, Observer { action ->
            when(action) {
                is UserAccountFragmentAction.GoToTheNotePageAction -> openUserNotes()
            }
        })

        binding.buttonToNotes.setOnClickListener {
            viewModel?.processAction(UserAccountFragmentAction.GoToTheNotePageAction)
        }

        return binding.root
    }

    private fun openUserNotes() {
        val fragment = NotesFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}
