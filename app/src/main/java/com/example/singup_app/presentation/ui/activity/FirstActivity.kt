package com.example.singup_app.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.singup_app.presentation.ui.fragment.LoginFragment_Launch
import com.example.singup_app.R

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

//        if (savedInstanceState == null) {
//            val fragment = LoginFragment_Launch()
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragment_main, fragment)
//                .commit()
//        }
        }
    }
