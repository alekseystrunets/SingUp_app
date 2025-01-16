package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.os.CountDownTimer

class CountdownView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 100f
        textAlign = Paint.Align.CENTER
    }

    private val backgroundPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL
    }

    private var count = 10
    private var backgroundAlpha = 255

    init {
        startCountdown()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(width, height) / 2f - 20 // Отступ от краев

        // Обновляем прозрачность в зависимости от времени
        backgroundAlpha = (255 * (count / 10f)).toInt().coerceIn(0, 255)
        backgroundPaint.alpha = backgroundAlpha

        canvas.drawOval(centerX - radius, centerY - radius, centerX + radius, centerY + radius, backgroundPaint)
        canvas.drawText(count.toString(), centerX, centerY + textPaint.textSize / 2, textPaint)
    }

    private fun startCountdown() {
        object : CountDownTimer(10000, 1000) { // 10 секунд, с интервалом 1 секунда
            override fun onTick(millisUntilFinished: Long) {
                count = (millisUntilFinished / 1000).toInt() // Обновляем счетчик
                invalidate() // Перерисовываем View
            }

            override fun onFinish() {
                count = 0 // Устанавливаем 0, когда таймер заканчивается
                invalidate() // Перерисовываем View
            }
        }.start()
    }
}