package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import com.example.singup_app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PrFragment : Fragment() {
    private val job = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pr, container, false)

        // Инициализация прогресс-бара
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        // Запускаем корутину для задержки
        lifecycleScope.launch {
            simulateLoading()
        }

        return view
    }

    private suspend fun simulateLoading() {
        // Имитация загрузки
        kotlinx.coroutines.delay(3000) // Задержка на 3 секунды

        // Получаем переданные данные
        val header = arguments?.getString("header", "") ?: ""
        val date = arguments?.getString("date", "") ?: ""
        val message = arguments?.getString("message", "") ?: ""

        // Переход на NotesFragment после задержки
        val notesFragment = NotesFragment().apply {
            arguments = Bundle().apply {
                putString("header", header)
                putString("date", date)
                putString("message", message)
            }
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_main, notesFragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Отменяем корутину при уничтожении фрагмента
    }
}