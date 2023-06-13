package com.example.myfirstgameprojectkotlin

import android.content.Context

class Player(context: Context, joystick: Joystick, positionX: Float, positionY: Float, radius : Float)
    :Circle(context, positionX,positionY,radius){
    companion object {
        const val SPEED_PIXELS_PER_SECOND =400F
        const val MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS
    }

    private val joystick:Joystick
    init{
        this.joystick=joystick
    }


    override fun update() {
        velocityX=joystick.getActuatorX()*MAX_SPEED
        velocityY=joystick.getActuatorY()*MAX_SPEED
        positionX+=velocityX
        positionY+=velocityY
        if (velocityX!=0f || velocityY!=0f){
            var distance : Float  = Utils.getDistanceBetweenPoints(0f,0f,velocityX,velocityY)
            directionXinGame=velocityX/distance
            directionYinGame=velocityY/distance
        }
    }





}















