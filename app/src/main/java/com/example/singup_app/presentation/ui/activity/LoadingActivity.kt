package com.example.singup_app.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.singup_app.R
import com.example.singup_app.databinding.ActivityLoadingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoadingActivity : AppCompatActivity() {

    private var _binding: ActivityLoadingBinding? = null
    private val binding : ActivityLoadingBinding get() = _binding!!

    private var progressStatus = 0
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoadingBinding.inflate(layoutInflater)

        setContentView(binding.root)


        scope.launch {
            for (i in 1..100) {
                kotlinx.coroutines.delay(30)
                progressStatus = i
                binding.progressBar.progress = progressStatus
            }
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}