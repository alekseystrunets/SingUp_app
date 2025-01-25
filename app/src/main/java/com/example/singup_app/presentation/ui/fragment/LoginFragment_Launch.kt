package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.singup_app.R
import com.example.singup_app.presentation.ui.activity.CustomView

class LoginFragment_Launch : Fragment() {

    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    private lateinit var userLog: EditText
    private lateinit var buttonReg: Button
    private var customView: CustomView? = null

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
        customView = view.findViewById(R.id.customView)

        buttonReg.setOnClickListener {
            if (validateInputs()) {
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
                customView?.setCount(1) // Устанавливаем ошибку
                showToast("Не все поля заполнены")
                false
            }
            !isValidEmail(email) -> {
                customView?.setCount(1) // Устанавливаем ошибку
                showToast("Некорректный адрес электронной почты")
                false
            }
            password.length < 8 -> {
                customView?.setCount(1) // Устанавливаем ошибку
                showToast("Пароль должен содержать не менее 8 символов")
                false
            }
            else -> {
                customView?.setCount(0) // Сбрасываем ошибку
                true
            }
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
        bundle.putString("email", userEmail.text.toString().trim())
        bundle.putString("login", userLog.text.toString().trim())

        // Передаем данные через arguments
        fragment.arguments = bundle

        // Переход на UserAccountFragment
        findNavController().navigate(R.id.action_loginFragment_Launch_to_userAccountFragment, bundle)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}