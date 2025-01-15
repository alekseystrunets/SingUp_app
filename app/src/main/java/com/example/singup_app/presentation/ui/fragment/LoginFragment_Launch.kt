package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.singup_app.R
import com.example.singup_app.databinding.FragmentLoginBinding

class LoginFragment_Launch : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding:FragmentLoginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
//        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            if (validateInputs()) {
                openUserAccountFragment()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val email = binding.userEmail.text.toString().trim()
        val password = binding.userPassword.text.toString().trim()
        val login = binding.userLogin.text.toString().trim()

        return when {
            email.isEmpty() || login.isEmpty() || password.isEmpty() -> {
                showToast("Не все поля заполнены")
                false
            }
            !isValidEmail(email) -> {
                showToast("Некорректный адрес электронной почты")
                false
            }
            password.length < 8 -> {
                showToast("Пароль должен содержать не менее 8 символов")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun openUserAccountFragment() {
        // Создаем новый экземпляр UserAccountFragment
        val fragment = UserAccountFragment()

        // Подготовка данных для передачи
        val bundle = Bundle()
        bundle.putString("email", binding.userEmail.text.toString().trim())
        bundle.putString("login", binding.userLogin.text.toString().trim())

        // Передаем данные через arguments
        fragment.arguments = bundle

        // Переход на UserAccountFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
