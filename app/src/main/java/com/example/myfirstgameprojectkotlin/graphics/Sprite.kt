package com.example.myfirstgameprojectkotlin.graphics

import android.graphics.Canvas
import android.graphics.Rect

class Sprite(spriteSheet: SpriteSheet, rect: Rect) {

    private var rect: Rect
    private var spriteSheet: SpriteSheet

    init {
        this.spriteSheet = spriteSheet
        this.rect=rect
    }
    fun draw(canvas: Canvas, x: Int, y: Int) {
        canvas.drawBitmap(
            spriteSheet.getBitmap(),
            rect,
            Rect(x,y,x+rect.height(),y+rect.width()),
            null
        )


    }

    fun getHeight(): Int {
        return rect.height()
    }

    fun getWidth(): Int {
        return rect.width()
    }

}








