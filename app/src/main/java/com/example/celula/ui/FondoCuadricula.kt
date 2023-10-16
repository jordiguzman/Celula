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
import kotlin.random.Random


class FondoCuadricula(context: Context): View(context) {

    private val random = Random(System.currentTimeMillis())
    private val numRows = 12
    private val numColumns = 20
    private val countArray = Array(numRows) { IntArray(numColumns) }
    private val colorArray = Array(numRows) { IntArray(numColumns) }
    private var count = 0
    private var celula: Celula? = null
    private val tileSizeWith = (width / numRows)
    private val tileSizeHeight = (height / numColumns)
    private var  celulaX = 0f
    private var celulaY =0f

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height
        val tileSizeWith = width / 12
        val tileSizeHeight = height / 20
        Log.d(TAG, "$width--$height")
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

        //celula?.draw(canvas, x, y, tileSizeWith.toFloat(), tileSizeHeight.toFloat())



    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Calcula la posición de la cuadrícula donde se tocó
                val touchedX = (event.x / tileSizeWith).toInt()
                val touchedY = (event.y / tileSizeHeight).toInt()

                // Calcula las coordenadas del centro del cuadrado tocado
                celulaX = touchedX.toFloat()
                celulaY = touchedY.toFloat()





                // Ajusta las coordenadas de la célula para que esté en el centro del cuadrado tocado
                celulaX += tileSizeWith / 2
                celulaY += tileSizeHeight / 2
                // Crea una instancia de Celula en el centro del cuadrado tocado
                celula = Celula(context)
                // Redibuja la vista para mostrar la célula

            }
        }
        return true
    }




    private val paint = Paint()
}





