package com.example.singup_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_password)
        val userLog: EditText = findViewById(R.id.user_login)
        val buttonReg: Button = findViewById(R.id.login_button)

        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validateLowerCase(str: String): Boolean {
            return when {
                str.all { it.isUpperCase() } -> {
                    false
                }

                str.any { it.isLowerCase() } -> {
                    true
                }

                else -> {
                    true
                }
            }
        }

        fun isDigitsOnly(str: String): Boolean {
            return str.all { it.isDigit() }
        }

        fun containsDigit(str: String): Boolean {
            return str.any { it.isDigit() }
        }

        buttonReg.setOnClickListener {
            val email = userEmail.text.toString().trim()
            val password = userPass.text.toString().trim()
            val login = userLog.text.toString().trim()
            if (email == "" || login == "" || password == "") {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (!isValidEmail(email)) {
                Toast.makeText(this, "Некорректный адрес электронной почты", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            } else if (password.length < 8) {
                Toast.makeText(
                    this,
                    "Пароль должен содержать не менее 8 символов",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else if (!validateLowerCase(email) || !validateLowerCase(login) || !validateLowerCase(
                    password
                )
            ) {
                Toast.makeText(
                    this,
                    "Не корректно введеные данные",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else if (isDigitsOnly(email) || isDigitsOnly(login) || isDigitsOnly(password)) {
                Toast.makeText(this, "Поля не должны содержать только цифры", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            } else if (!containsDigit(password)) {
                Toast.makeText(
                    this,
                    "Пароль должен содержать хотя бы одну цифру",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            } else {
                val intent = Intent(this, UserAccount::class.java)
                intent.putExtra("USER_LOGIN",login)
                intent.putExtra("USER_EMAIL",email)
                startActivity(intent)
            }
        }
    }
}

