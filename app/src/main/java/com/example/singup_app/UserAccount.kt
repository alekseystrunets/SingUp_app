package com.example.singup_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)
        val login = intent.getStringExtra("USER_LOGIN")
        val email = intent.getStringExtra("USER_EMAIL")
        val intent = Intent(this, Notes::class.java)
        startActivity(intent)

        val accountLoginDuplicate: AppCompatTextView = findViewById(R.id.user_account_login)
        accountLoginDuplicate.text = login ?: "Логин не указан"

        val accountEmailDuplicate: AppCompatTextView = findViewById(R.id.user_account_email1)
        accountEmailDuplicate.text = email ?: "Email не указан"
    }
}
