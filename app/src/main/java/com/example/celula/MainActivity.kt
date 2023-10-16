package com.example.celula

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.celula.ui.CapaCelula
import com.example.celula.ui.Celula
import com.example.celula.ui.FabButton
import com.example.celula.ui.FondoCuadricula


class MainActivity : AppCompatActivity() {




    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = FrameLayout(this)
        container.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )



        val fondo = FondoCuadricula(this)
        //val cel = CapaCelula(this)
        val celula = Celula(this)
        val fab = FabButton(this)

        //container.addView(fab)
        container.addView(fondo)
        container.addView(celula)

        setContentView(container)

    }




}

