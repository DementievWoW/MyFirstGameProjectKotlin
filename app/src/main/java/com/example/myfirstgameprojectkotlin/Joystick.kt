package com.example.myfirstgameprojectkotlin

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.pow
import kotlin.math.sqrt

class Joystick(centerPositionX:Int, centerPositionY:Int,outerCircleRadius:Int,innerCircleRadius:Int ) {
    private var actuatorY: Float = 0.0f
    private var actuatorX: Float = 0.0f
    private var joystickCenterToTouchDistance: Float = 0.0f
    private var innerCirclePaint: Paint
    private var outerCirclePaint: Paint
    private var innerCircleRadius: Int
    private var outerCircleRadius: Int
    private var innerCircleCenterPositionY: Int
    private var innerCircleCenterPositionX: Int
    private var outerCircleCenterPositionY: Int
    private var outerCircleCenterPositionX: Int
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
            outerCircleCenterPositionX.toFloat(),
            outerCircleCenterPositionY.toFloat(),
            outerCircleRadius.toFloat(),
            outerCirclePaint
        )
        canvas.drawCircle(
            innerCircleCenterPositionX.toFloat(),
            innerCircleCenterPositionY.toFloat(),
            innerCircleRadius.toFloat(),
            innerCirclePaint
        )
    }

    fun update() {
        updateInnerCirclePosition()
    }

    private fun updateInnerCirclePosition() {
        innerCircleCenterPositionX=((outerCircleCenterPositionX+actuatorX*outerCircleRadius).toInt())
        innerCircleCenterPositionY=((outerCircleCenterPositionY+actuatorY*outerCircleRadius).toInt())
    }

    fun isPressed(touchPositionX: Float, touchPositionY: Float): Boolean {

        joystickCenterToTouchDistance=getDistance(touchPositionX, touchPositionY)


        return joystickCenterToTouchDistance < outerCircleRadius
    }

    private fun getDistance(touchPositionX: Float, touchPositionY: Float) =
        sqrt(
            (outerCircleCenterPositionX - touchPositionX).toDouble().pow(2.0)
                    +
                    (outerCircleCenterPositionY - touchPositionY).toDouble().pow(2.0)
        ).toFloat()

    fun setIsPressed(isPressed: Boolean) {
        this.isPressed=isPressed
    }

    fun getIsPressed(): Boolean {
    return isPressed
    }

    fun setActuator(touchPositionX: Float, touchPositionY: Float) {
        var deltaDistance=getDistance(touchPositionX,touchPositionY)
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












