package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class Player(context: Context, positionX : Float, positionY: Float, radius: Float) {
    private var positionX : Float
    private var positionY : Float
    private var radius : Float
    private var paint : Paint = Paint()
    init{
        this.positionX=positionX
        this.positionY=positionY
        this.radius=radius
        var color = ContextCompat.getColor(context, R.color.purple_200)
        paint.color = color
    }

    fun draw(canvas: Canvas) {
        canvas.drawCircle(positionX, positionY,radius,paint)
    }

    fun update() {

    }

    fun setPosition(positionX: Float, positionY: Float) {
        this.positionX=positionX
        this.positionY=positionY
    }

}















