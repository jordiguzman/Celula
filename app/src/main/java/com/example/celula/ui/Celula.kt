package com.example.celula.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Celula (context: Context) : View(context) {

    private val paint = Paint()
    fun draw(canvas: Canvas, x: Float, y: Float, tileSizeWidth: Float, tileSizeHeight: Float) {
        // Dibuja la célula en el centro del cuadrado
        paint.color = Color.RED // Cambia el color como desees
        canvas.drawRect(
            x + tileSizeWidth / 4, y + tileSizeHeight / 4,
            x + 3 * tileSizeWidth / 4, y + 3 * tileSizeHeight / 4,
            paint
        )
    }



}