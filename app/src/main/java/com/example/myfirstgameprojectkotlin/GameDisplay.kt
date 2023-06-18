package com.example.myfirstgameprojectkotlin

import android.graphics.Rect
import com.example.myfirstgameprojectkotlin.gameobject.GameObject


class GameDisplay(
    private val widthPixels: Int,
    private val heightPixels: Int,
    centerObject: GameObject
) {
    val DISPLAY_RECT: Rect
    private val centerObject: GameObject
    private val displayCenterX: Float
    private val displayCenterY: Float
    private var gameToDisplayCoordinatesOffsetX = 0.0f
    private var gameToDisplayCoordinatesOffsetY = 0.0f
    private var gameCenterX = 0.0f
    private var gameCenterY = 0.0f

    init {
        DISPLAY_RECT = Rect(0, 0, widthPixels, heightPixels)
        this.centerObject = centerObject
        displayCenterX = widthPixels / 2.0f
        displayCenterY = heightPixels / 2.0f
        update()
    }

    fun update() {
        gameCenterX = centerObject.positionX
        gameCenterY = centerObject.positionY
        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY
    }

    fun gameToDisplayCoordinatesX(x: Float): Float {
        return x + gameToDisplayCoordinatesOffsetX
    }

    fun gameToDisplayCoordinatesY(y: Float): Float {
        return y + gameToDisplayCoordinatesOffsetY
    }

    fun getGameRect(): Rect {
        return Rect(
            (gameCenterX-widthPixels/2).toInt(),
            (gameCenterY-heightPixels/2).toInt(),
            (gameCenterX+widthPixels/2).toInt(),
            (gameCenterY+heightPixels/2).toInt()

        )
    }
}
//class GameDisplay(widthPixels: Int, heightPixels: Int, centerObject: GameObject) {
//    var DISPLAY_RECT: Rect = Rect(0,0,widthPixels,heightPixels)
//    private var centerObject: GameObject
//    private var gameCenterY: Float = 0.0f
//    private var gameCenterX: Float = 0.0f
//    private var displayCenterY: Float
//    private var displayCenterX: Float
//    private var gameToDisplayCoordinateOffsetY: Float = 0.0f
//    private var gameToDisplayCoordinateOffsetX: Float = 0.0f
//    private var widthPixels : Int = widthPixels
//    private var heightPixels : Int = heightPixels
//
//    init {
//        this.centerObject=centerObject
//        displayCenterX=widthPixels/2.0f
//        displayCenterY=heightPixels/2.0f
//    }
//    fun update(){
//        gameCenterX=centerObject.getPositionX()
//        gameCenterY=centerObject.getPositionY()
//        gameToDisplayCoordinateOffsetX=displayCenterX-gameCenterX
//        gameToDisplayCoordinateOffsetY=displayCenterY-gameCenterY
//    }
//    fun gameToDisplayCoordinatesX(positionX: Float): Float {
//        return positionX+gameToDisplayCoordinateOffsetX
//    }
//
//    fun gameToDisplayCoordinatesY(positionY: Float): Float {
//        return positionY+gameToDisplayCoordinateOffsetY
//    }
//
//    fun getGameRect(): Rect {
//        return Rect(
//            (gameCenterX-widthPixels/2).toInt(),
//            (gameCenterY-heightPixels/2).toInt(),
//            (gameCenterX+widthPixels/2).toInt(),
//            (gameCenterY+heightPixels/2).toInt()
//
//        )
//    }
//
//}
