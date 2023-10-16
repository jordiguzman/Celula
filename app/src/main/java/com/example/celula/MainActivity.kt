package com.example.celula

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.celula.ui.CapaCelula
import com.example.celula.ui.FabButton
import com.example.celula.ui.FondoCuadricula


class MainActivity : AppCompatActivity(), View.OnTouchListener {

    private lateinit var fondoCuadricula: FondoCuadricula
    private lateinit var gestureDetector: GestureDetector

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = FrameLayout(this)
        container.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        fondoCuadricula = FondoCuadricula(this)
        gestureDetector = GestureDetector(this, MyGestureListener())
        val cel = CapaCelula(this, fondoCuadricula)
        val fondo = FondoCuadricula(this)
        val fab = FabButton(this)


        val capaCelula = CapaCelula(this, fondoCuadricula) // Pasa la referencia de FondoCuadricula
        capaCelula.setFondoCuadricula(fondoCuadricula)
        capaCelula.setOnTouchListener(this) // Agrega OnTouchListener a CapaCelula


        fondoCuadricula.setOnTouchListener(this)
        container.addView(capaCelula)
        container.addView(fondo)
        container.addView(cel)
        //container.addView(fab)


        setContentView(container)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            // Obtén las coordenadas del toque
            val x = e.x.toInt()
            val y = e.y.toInt()

            // Calcula la fila y columna correspondiente en tu matriz
            val tileSizeWith = fondoCuadricula.width / fondoCuadricula.numRows
            val tileSizeHeight = fondoCuadricula.height / fondoCuadricula.numColumns
            val row = x / tileSizeWith
            val column = y / tileSizeHeight

            if (row >= 0 && row < fondoCuadricula.numRows && column >= 0 && column < fondoCuadricula.numColumns) {
                val color = fondoCuadricula.colorArray[row][column]
                // Muestra el valor de color, por ejemplo, en un Toast
                Log.d(TAG, "Color: $color")
                //Toast.makeText(MainActivity(this), "Color: $color", Toast.LENGTH_SHORT).show()
            }

            return true
        }
    }
}

