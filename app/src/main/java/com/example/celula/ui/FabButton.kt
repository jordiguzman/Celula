package com.example.celula.ui

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.example.celula.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabButton (context: Context) : FrameLayout(context){
    private val fabSize = resources.getDimensionPixelSize(R.dimen.fab_size)
    private val fabMargin = resources.getDimensionPixelSize(R.dimen.fab_margin)
    private val fabIcon: Drawable = ContextCompat.getDrawable(context, R.drawable.fab_image)!!

    init {
        // Configure the FrameLayout
        val layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.BOTTOM or Gravity.END
        layoutParams.setMargins(fabMargin, fabMargin, fabMargin, fabMargin)
        setLayoutParams(layoutParams)

        // Create the Floating Action Button (FAB)
        val fab = FloatingActionButton(context)
        fab.layoutParams = ViewGroup.LayoutParams(fabSize, fabSize)
        fab.backgroundTintList = ContextCompat.getColorStateList(context, R.color.red)
        fab.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)

        // Create an ImageView to hold the icon
        val imageView = AppCompatImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(fabSize, fabSize)
        imageView.setImageDrawable(fabIcon)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE

        // Set an OnClickListener for the FAB
        fab.setOnClickListener {

        }
        // Add the ImageView to the FAB
        addView(imageView)


        // Add the FAB to the FrameLayout
        addView(fab)
    }
}