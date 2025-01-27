package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context.MODE_PRIVATE
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.singup_app.R
import com.example.singup_app.data.shared_preferences.User
import com.example.singup_app.data.UserRepository

class LoginFragment_Launch : Fragment() {

    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    private lateinit var userLog: EditText
    private lateinit var buttonReg: Button
    private var userRepository: UserRepository? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userEmail = view.findViewById(R.id.user_email)
        userPass = view.findViewById(R.id.user_password)
        userLog = view.findViewById(R.id.user_login)
        buttonReg = view.findViewById(R.id.login_button)

        val sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", MODE_PRIVATE)
        userRepository = UserRepository(sharedPreferences)

        // Устанавливаем обработчик нажатия кнопки регистрации
        buttonReg.setOnClickListener {
            if (validateInputs()) {
                saveUserData()
                showToast("Пользователь успешно зарегистрирован!")
                openUserAccountFragment()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val email = userEmail.text.toString().trim()
        val password = userPass.text.toString().trim()
        val login = userLog.text.toString().trim()

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

    private fun saveUserData() {
        val email = userEmail.text.toString().trim()
        val login = userLog.text.toString().trim()
        val password = userPass.text.toString().trim()

        // Сохраняем пользователя с помощью репозитория
        userRepository?.saveUser(User(email, login, password))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun openUserAccountFragment() {
        val fragment = UserAccountFragment()
        val bundle = Bundle()
        bundle.putString("email", userEmail.text.toString().trim())
        bundle.putString("login", userLog.text.toString().trim())
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}