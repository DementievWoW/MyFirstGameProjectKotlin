package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class Player(context: Context, positionX : Float, positionY: Float, radius: Float) {
    companion object {
        private const val SPEED_PIXELS_PER_SECOND =400F
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS
    }
    private var velocityY: Float = 0.0f
    private var velocityX: Float = 0.0f
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

    fun update(joystick: Joystick) {
        velocityX=joystick.getActuatorX()*MAX_SPEED
        velocityY=joystick.getActuatorY()*MAX_SPEED
        positionX+=velocityX
        positionY+=velocityY
    }

    fun setPosition(positionX: Float, positionY: Float) {
        this.positionX=positionX
        this.positionY=positionY
    }

}















