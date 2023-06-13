package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.BLUE
import android.graphics.Paint
import androidx.core.content.ContextCompat

abstract class Circle(context: Context, positionX: Float, positionY: Float, radius : Float) : GameObject(positionX, positionY){

    var radius : Float = 0.0f
    private var paint : Paint = Paint()
    init {
        this.radius=radius
        paint.color=BLUE
    }
    override fun draw(canvas: Canvas) {
        canvas.drawCircle(positionX, positionY,radius,paint)
    }

    companion object {
        fun isColliding(obj1: Circle, obj2: Circle): Boolean {
            var distance: Float = getDistanceBetweenObject(obj1,obj2)
            var distanceToCollision = obj1.getRadius() + obj2.getRadius()
            return distance<distanceToCollision
        }

    }

    internal fun getRadius(): Float {
        return radius
    }

}