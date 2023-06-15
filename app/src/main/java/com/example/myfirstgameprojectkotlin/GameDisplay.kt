package com.example.myfirstgameprojectkotlin

import com.example.myfirstgameprojectkotlin.gameobject.GameObject

class GameDisplay(widthPixels: Int, heightPixels: Int, centerObject: GameObject) {
    private var centerObject: GameObject
    private var gameCenterY: Float = 0.0f
    private var gameCenterX: Float = 0.0f
    private var displayCenterY: Float
    private var displayCenterX: Float
    private var gameToDisplayCoordinateOffsetY: Float = 0.0f
    private var gameToDisplayCoordinateOffsetX: Float = 0.0f

    init {
        this.centerObject=centerObject
        displayCenterX=widthPixels/2.0f
        displayCenterY=heightPixels/2.0f
    }
    fun update(){
        gameCenterX=centerObject.getPositionX()
        gameCenterY=centerObject.getPositionY()
        gameToDisplayCoordinateOffsetX=displayCenterX-gameCenterX
        gameToDisplayCoordinateOffsetY=displayCenterY-gameCenterY
    }
    fun gameToDisplayCoordinatesX(positionX: Float): Float {
        return positionX+gameToDisplayCoordinateOffsetX
    }

    fun gameToDisplayCoordinatesY(positionY: Float): Float {
        return positionY+gameToDisplayCoordinateOffsetY
    }

}
