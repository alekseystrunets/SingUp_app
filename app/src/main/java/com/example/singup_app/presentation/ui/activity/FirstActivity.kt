package com.example.singup_app.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.singup_app.presentation.ui.fragment.LoginFragment_Launch
import com.example.singup_app.R
import com.example.singup_app.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private var _binding : ActivityFirstBinding? = null
    private val binding : ActivityFirstBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = LoginFragment_Launch()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_main, fragment)
                .commit()
        }
        }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
    }