package com.example.myfirstgameprojectkotlin.graphics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

class SpriteSVG() {
//    private var rect: Rect
//    private var spriteSheet: SpriteSheet

//    init {
//        this.spriteSheet = spriteSheet
//        this.rect=rect
//    }
    fun draw(context: Context, canvas: Canvas, xmlSVG: Int) {
        var drawable: Drawable? = ContextCompat.getDrawable(context, xmlSVG)
        drawable?.draw(canvas)
    }

}
