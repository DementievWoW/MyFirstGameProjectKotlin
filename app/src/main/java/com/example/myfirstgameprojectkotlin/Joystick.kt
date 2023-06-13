package com.example.myfirstgameprojectkotlin

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Joystick(centerPositionX:Float, centerPositionY:Float,outerCircleRadius:Float,innerCircleRadius:Float ) {
    private var actuatorY: Float = 0.0f
    private var actuatorX: Float = 0.0f
    private var joystickCenterToTouchDistance: Float = 0.0f
    private var innerCirclePaint: Paint
    private var outerCirclePaint: Paint
    private var innerCircleRadius: Float
    private var outerCircleRadius: Float
    private var innerCircleCenterPositionY: Float
    private var innerCircleCenterPositionX: Float
    private var outerCircleCenterPositionY: Float
    private var outerCircleCenterPositionX: Float
    private var isPressed:Boolean = false

    init {
        outerCircleCenterPositionX=centerPositionX
        outerCircleCenterPositionY=centerPositionY
        innerCircleCenterPositionX=centerPositionX
        innerCircleCenterPositionY=centerPositionY
        this.outerCircleRadius = outerCircleRadius
        this.innerCircleRadius = innerCircleRadius

        outerCirclePaint= Paint();
        innerCirclePaint= Paint();

        outerCirclePaint.color = Color.GRAY
        outerCirclePaint.style = Paint.Style.FILL_AND_STROKE


        innerCirclePaint.color = Color.BLUE
        innerCirclePaint.style = Paint.Style.FILL_AND_STROKE

    }
    fun draw(canvas: Canvas) {

        canvas.drawCircle(
            outerCircleCenterPositionX,
            outerCircleCenterPositionY,
            outerCircleRadius,
            outerCirclePaint
        )
        canvas.drawCircle(
            innerCircleCenterPositionX,
            innerCircleCenterPositionY,
            innerCircleRadius,
            innerCirclePaint
        )
    }

    fun update() {
        updateInnerCirclePosition()
    }

    private fun updateInnerCirclePosition() {
        innerCircleCenterPositionX=(outerCircleCenterPositionX+actuatorX*outerCircleRadius)
        innerCircleCenterPositionY=(outerCircleCenterPositionY+actuatorY*outerCircleRadius)
    }

    fun isPressed(touchPositionX: Float, touchPositionY: Float): Boolean {

        joystickCenterToTouchDistance=Utils.getDistanceBetweenPoints(outerCircleCenterPositionX,outerCircleCenterPositionY,touchPositionX, touchPositionY)


        return joystickCenterToTouchDistance < outerCircleRadius
    }


    fun setIsPressed(isPressed: Boolean) {
        this.isPressed=isPressed
    }

    fun getIsPressed(): Boolean {
    return isPressed
    }

    fun setActuator(touchPositionX: Float, touchPositionY: Float) {
        var deltaDistance=Utils.getDistanceBetweenPoints(outerCircleCenterPositionX,outerCircleCenterPositionY,touchPositionX,touchPositionY)
        if (deltaDistance<outerCircleRadius){
            actuatorX = (touchPositionX - outerCircleCenterPositionX)/outerCircleRadius
            actuatorY = (touchPositionY - outerCircleCenterPositionY)/outerCircleRadius
        }else{
            actuatorX = (touchPositionX - outerCircleCenterPositionX)/deltaDistance
            actuatorY = (touchPositionY - outerCircleCenterPositionY)/deltaDistance
        }

    }

    fun resetActuator() {
        actuatorX=0.0f
        actuatorY=0.0f
    }

    fun getActuatorX(): Float {
        return actuatorX
    }

    fun getActuatorY(): Float {
        return actuatorY
    }

}












