package com.example.singup_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        if (savedInstanceState == null) {
            val fragment = LoginFragment_Launch()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_main, fragment)
                .commit()
        }
        }
    }
