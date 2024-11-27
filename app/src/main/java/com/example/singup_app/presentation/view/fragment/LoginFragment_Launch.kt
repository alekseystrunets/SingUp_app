package com.example.singup_app.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.singup_app.R
import com.example.singup_app.presentation.view.actions.ViewLFLAction
import com.example.singup_app.presentation.view_model.ViewModelCrNFragment
import com.example.singup_app.presentation.view_model.ViewModelLFL

class LoginFragment_Launch : Fragment() {
    private var viewModel: ViewModelLFL? = null

    private var userEmail: EditText? = null
    private var userPass: EditText? = null
    private var userLog: EditText? = null
    private var buttonReg: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelLFL::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userEmail = view.findViewById(R.id.user_email)
        userPass = view.findViewById(R.id.user_password)
        userLog = view.findViewById(R.id.user_login)
        buttonReg = view.findViewById(R.id.login_button)

        viewModel?.publicLiveData?.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ViewLFLAction.ShowToast -> showToast(action.message)
                is ViewLFLAction.ValidationSuccess -> openUserAccountFragment()
            }
        }

        buttonReg?.setOnClickListener {
            val email = userEmail?.text.toString().trim()
            val password = userPass?.text.toString().trim()
            val login = userLog?.text.toString().trim()
            viewModel?.validateInputs(email, login, password) // Передаем параметры для валидации
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun openUserAccountFragment() {
        val fragment = UserAccountFragment()
        val bundle = Bundle().apply {
            putString("email", userEmail?.text.toString().trim())
            putString("login", userLog?.text.toString().trim())
        }
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}
