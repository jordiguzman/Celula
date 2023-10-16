package com.example.celula.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.MotionEvent
import android.view.View


@SuppressLint("ViewConstructor")
class CapaCelula(context: Context, private var fondoCuadricula: FondoCuadricula) : View(context) {

    private var posX = 0f
    private var posY = 0f
    private var radius = 30f
    private val animationThread: HandlerThread = HandlerThread("AnimationThread")
    private val animationHandler: Handler
    private val mainHandler: Handler = Handler(Looper.getMainLooper())
    private var speed = 10f // Velocidad de movimiento
    private val circles = mutableListOf<Circle>() // Lista de círculos


    init {
        animationThread.start()
        animationHandler = Handler(animationThread.looper)
        startAnimation()
    }

    private fun startAnimation() {
        animationHandler.post(object : Runnable {
            override fun run() {
                // Actualizar la posición horizontal del círculo
                posX += speed

                // Redibujar la vista en el hilo principal
                mainHandler.post {
                    invalidate()
                }

                // Repetir la animación
                animationHandler.postDelayed(this, 30) // 60 FPS (aproximadamente)
            }
        })
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.setARGB(255, 255, 0, 0)
        paint.isAntiAlias
        canvas.drawCircle(posX, posY, radius, paint)
        for (circle in circles) {

            paint.color = circle.color
            canvas.drawCircle(circle.x, circle.y, circle.radius, paint)
        }
        if (posX - radius < 0 || posX + radius > width) {
            speed *= -1
        }

    }
    // Método para establecer la referencia a FondoCuadricula
    fun setFondoCuadricula(fondoCuadricula: FondoCuadricula) {
        this.fondoCuadricula = fondoCuadricula
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Solo manejar eventos táctiles cuando no se toca la parte superpuesta con FondoCuadricula
        // Verifica si la referencia a fondoCuadricula es nula
        if (event.x < fondoCuadricula.width && event.y < fondoCuadricula.height) {
            return super.onTouchEvent(event)
        }
        posX = event.x
        posY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val newCircle = Circle(
                    event.x ,
                    event.y ,
                    radius,
                    Color.BLUE,
                    2f)
                circles.add(newCircle)
                createNewCircleThread(newCircle)
                //invalidate()
                return true
            }
        }


        return super.onTouchEvent(event)
    }
    private fun createNewCircleThread(circle: Circle) {
        val thread = HandlerThread("CircleThread")
        thread.start()
        val handler = Handler(thread.looper)
        handler.post {
            while (circle.x > 0 && circle.x < width) {
                circle.x += circle.speed
                mainHandler.post {
                    invalidate()
                }
                Thread.sleep(30)
            }
            thread.quit()
        }
    }

    data class Circle(var x: Float, val y: Float, val radius: Float, val color: Int, val speed: Float)
}
