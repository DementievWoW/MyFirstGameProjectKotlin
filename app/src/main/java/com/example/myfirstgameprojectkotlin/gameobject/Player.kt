package com.example.myfirstgameprojectkotlin.gameobject

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import com.example.myfirstgameprojectkotlin.*
import com.example.myfirstgameprojectkotlin.gameinterface.Joystick
import com.example.myfirstgameprojectkotlin.graphics.SpriteSVG

class Player(context: Context, color:Int, joystick: Joystick, positionX: Float,
             positionY: Float, radius : Float, spriteSVG: SpriteSVG)
    : Circle(context,color, positionX,positionY,radius){
    companion object {
        const val SPEED_PIXELS_PER_SECOND =400F
        const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
        var MAX_HEALTH_POINTS: Int = 10
    }

    private var context: Context
    private var spriteSVG: SpriteSVG
    private var healthPoints: Int
    private var healthBar: HealthBar
    private val joystick: Joystick
    private var spriteID=R.drawable.maghero_7
    init{
        this.spriteSVG=spriteSVG
        this.context=context
        this.joystick=joystick
        this.healthBar = HealthBar(context, this)
        this.healthPoints = MAX_HEALTH_POINTS

    }


    override fun update() {
        velocityX=joystick.getActuatorX()* MAX_SPEED
        velocityY=joystick.getActuatorY()* MAX_SPEED
        positionX+=velocityX
        positionY+=velocityY
        if (velocityX!=0f || velocityY!=0f){
            var distance : Float  = Utils.getDistanceBetweenPoints(0f, 0f, velocityX, velocityY)
            directionXinGame=velocityX/distance
            directionYinGame=velocityY/distance
        }
    }

 override fun draw(canvas: Canvas, gameDisplay: GameDisplay){
     spriteSVG.draw(this.context, canvas, spriteID)
     healthBar.draw(canvas,gameDisplay)
}

    fun getHealthPoints(): Int {
        return healthPoints
    }

    fun setHealthPoints(healthPoints: Int) {
        if(healthPoints>=0){
            this.healthPoints=healthPoints
        }

    }


}















