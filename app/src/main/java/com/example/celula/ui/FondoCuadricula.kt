package com.example.celula.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random


class FondoCuadricula(context: Context): View(context) {

    private val random = Random(System.currentTimeMillis())
    val numRows = 12
    val numColumns = 20
    private val countArray = Array(numRows) { IntArray(numColumns) }
    val colorArray = Array(numRows) { IntArray(numColumns) }
    private var count = 0

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height
        val tileSizeWith = width / 12
        val tileSizeHeight = height / 20

        // Draw the checkerboard squares
        for (i in 0 until 12) {
            for (j in 0 until 20) {
                val x: Float = (i * tileSizeWith).toFloat()
                val y: Float = (j * tileSizeHeight).toFloat()
                count++
                val randomColor = Color.rgb(random.nextInt(1), random.nextInt(256), random.nextInt(1))
                paint.color = randomColor
                countArray[i][j] = count
                colorArray[i][j] = randomColor
                /*Log.d(TAG, countArray[i][j].toString() )
                Log.d(TAG, colorArray[i][j].toString() )*/
                canvas.drawRect(
                    x,
                    y, (x + tileSizeWith), (y + tileSizeHeight), paint)
            }
        }

    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Obtén las coordenadas del toque
                val x = event.x.toInt()
                val y = event.y.toInt()

                // Calcula la fila y columna correspondiente en tu matriz
                val tileSizeWith = width / numRows
                val tileSizeHeight = height / numColumns
                val row = x / tileSizeWith
                val column = y / tileSizeHeight

                if (row in 0..<numRows && column >= 0 && column < numColumns) {
                    val color = colorArray[row][column]
                    // Muestra el valor de color, por ejemplo, en un Toast
                    //Toast.makeText(context, "Color: $color", Toast.LENGTH_SHORT).show()
                    Log.d(TAG,"Color: $color" )
                }
            }
        }
        return true
    }
    private val paint = Paint()
}





