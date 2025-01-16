package com.example.singup_app.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.singup_app.R
import com.example.customview.CountdownView
import com.example.customview.FlipSquare

class CombinedViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем ссылки на ваши кастомные View
        val countdownView = view.findViewById<CountdownView>(R.id.countdown_view)
        val flipSquare = view.findViewById<FlipSquare>(R.id.flip_square)

        // Запускаем анимацию FlipSquare
        flipSquare.startFlipAnimation()
    }
}