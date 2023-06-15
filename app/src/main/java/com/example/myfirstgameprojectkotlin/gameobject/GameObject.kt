package com.example.myfirstgameprojectkotlin.gameobject

import android.graphics.Canvas
import kotlin.math.pow
import kotlin.math.sqrt

abstract class GameObject( positionX : Float, positionY: Float,) {
    protected var directionXinGame : Float = 1.0f
    protected var directionYinGame : Float = 1.0f
    protected var positionX : Float = 0.0f
    protected var positionY : Float = 0.0f
    protected var velocityY : Float = 0.0f
    protected var velocityX : Float = 0.0f
    init {
        this.positionX=positionX
        this.positionY=positionY
    }
    abstract fun draw(canvas: Canvas)
    abstract fun update()
     internal fun getPositionX(): Float {
        return this.positionX
    }
    internal fun getPositionY(): Float {
        return this.positionY
    }

    internal fun getDirectionXinGame(): Float {
        return directionXinGame
    }
   internal fun getDirectionYinGame(): Float {
        return directionYinGame
    }
    companion object{
        fun getDistanceBetweenObject(gameObject1: GameObject, gameObject2: GameObject) : Float{
            return sqrt(
                (gameObject2.getPositionX() - gameObject1.getPositionX()).toDouble().pow(2.0)
                        +
                        (gameObject2.getPositionY() - gameObject1.getPositionY()).toDouble().pow(2.0)
            ).toFloat()
        }
    }
}
