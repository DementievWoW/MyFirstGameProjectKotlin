package com.example.myfirstgameprojectkotlin.gameobject

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.example.myfirstgameprojectkotlin.GameDisplay

abstract class Circle(context: Context,color : Int, positionX: Float, positionY: Float, radius : Float) : GameObject(positionX, positionY){

    var radius : Float = 0.0f
    private var paint : Paint = Paint()
    init {
        this.radius=radius
        this.paint.color=color
    }
    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        canvas.drawCircle(
            gameDisplay.gameToDisplayCoordinatesX(positionX),
            gameDisplay.gameToDisplayCoordinatesY(positionY),
            radius,
            paint)
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