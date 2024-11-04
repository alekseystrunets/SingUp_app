package com.example.singup_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_password)
        val userLog: EditText = findViewById(R.id.user_login)
        val buttonReg: Button = findViewById(R.id.login_button)

        buttonReg.setOnClickListener {
            if (validateInputs(userEmail, userPass, userLog)) {
                openUserAccountFragment()
            }
        }
    }

    private fun validateInputs(userEmail: EditText, userPass: EditText, userLog: EditText): Boolean {
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
            !validateLowerCase(email) || !validateLowerCase(login) || !validateLowerCase(password) -> {
                showToast("Не корректно введенные данные")
                false
            }
            isDigitsOnly(email) || isDigitsOnly(login) || isDigitsOnly(password) -> {
                showToast("Поля не должны содержать только цифры")
                false
            }
            !containsDigit(password) -> {
                showToast("Пароль должен содержать хотя бы одну цифру")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validateLowerCase(str: String): Boolean {
        return str.any { it.isLowerCase() }
    }

    private fun isDigitsOnly(str: String): Boolean {
        return str.all { it.isDigit() }
    }

    private fun containsDigit(str: String): Boolean {
        return str.any { it.isDigit() }
    }

    private fun openUserAccountFragment() {
        val fragment = UserAccountFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_account_user, fragment)
            .commit()
    }
}