package com.example.singup_app.presentation.ui.activity

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.isVisible
import com.example.singup_app.R

class CustomView(val myContext: Context, attrs: AttributeSet?) : View(myContext, attrs) {
    var count: Int? = null
    private val handler = Handler(Looper.getMainLooper())

    init {
        initAttrs(attrs)
    }

    fun setCount(count: Int) {
        this.count = count
        isVisible = count > 0 // Управление видимостью в зависимости от count
        invalidate()

        if (count > 0) {
            scheduleHide()
        }
    }

    private fun scheduleHide() {
        handler.postDelayed({
            setCount(0)
        }, 3000)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray: TypedArray = myContext.obtainStyledAttributes(attrs, R.styleable.CustomView)
        val count = typedArray.getInt(R.styleable.CustomView_customViewValue, 0)
        this.count = count
        isVisible = count > 0
        typedArray.recycle()
    }

    val paint = Paint()

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint.color = Color.WHITE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Лог для проверки вызова onDraw
        Log.d("CustomView", "onDraw called, count: $count")


        val rectLeft = 100f
        val rectTop = 100f
        val rectRight = 1000f
        val rectBottom = 300f


        val cornerRadius = 50f


        val rectPaint = Paint().apply {
            color = Color.DKGRAY
            style = Paint.Style.FILL
        }
        canvas.drawRoundRect(rectLeft, rectTop, rectRight, rectBottom, cornerRadius, cornerRadius, rectPaint)


        if (count != null && count!! > 0) {

            val textPaint = Paint().apply {
                color = Color.WHITE
                textSize = 50f // Размер текста
                textAlign = Paint.Align.CENTER
            }


            val textX = (rectLeft + rectRight) / 2
            val textY = (rectTop + rectBottom) / 2 - (textPaint.descent() + textPaint.ascent()) / 2

            canvas.drawText("Данные введены неправильно", textX, textY, textPaint)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacksAndMessages(null)
    }
}