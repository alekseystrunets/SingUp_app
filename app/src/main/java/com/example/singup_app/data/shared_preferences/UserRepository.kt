package com.example.singup_app.data

import android.content.SharedPreferences
import com.example.singup_app.data.shared_preferences.User

class UserRepository(private val sharedPreferences: SharedPreferences) {

    fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putString("email", user.email)
            putString("login", user.login)
            putString("password", user.password)
            apply()
        }
    }

    fun getUser(): User? {
        val email = sharedPreferences.getString("email", null)
        val login = sharedPreferences.getString("login", null)
        val password = sharedPreferences.getString("password", null)
        return if (email != null && login != null && password != null) {
            User(email, login, password)
        } else {
            null
        }
    }
}