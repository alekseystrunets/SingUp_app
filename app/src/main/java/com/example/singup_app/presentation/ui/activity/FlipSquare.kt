package com.example.customview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Camera
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class FlipSquare @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLUE
        textSize = 100f
        textAlign = Paint.Align.CENTER
    }

    private var currentText = "F"
    private var rotationY = 0f
    private var isAnimating = false
    private val camera = Camera()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val size = width.coerceAtMost(height)

        canvas.save()
        canvas.translate(size / 2f, size / 2f)

        camera.save()
        camera.rotateY(rotationY)
        camera.applyToCanvas(canvas)
        canvas.drawRect(-size / 2f, -size / 2f, size / 2f, size / 2f, paint)
        canvas.drawText(currentText, 0f, 10f, Paint().apply {
            color = Color.WHITE
            textSize = 48f
            textAlign = Paint.Align.CENTER
        })
        camera.restore()

        canvas.restore()
    }

    fun startFlipAnimation() {
        if (isAnimating) return

        isAnimating = true
        val animator = ObjectAnimator.ofFloat(this, "rotationY", 0f, 180f)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            currentText = if (animation.animatedFraction > 0.5) "T" else "F"
            invalidate() // Перерисовываем View
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                isAnimating = false
                startFlipAnimation() // Запускаем анимацию снова
            }
        })
        animator.start()
    }

    // Переопределяем метод setRotationY
    override fun setRotationY(rotation: Float) {
        super.setRotationY(rotation)
        rotationY = rotation
        invalidate()
    }
}