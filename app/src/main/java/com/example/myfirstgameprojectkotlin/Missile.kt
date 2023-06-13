package com.example.myfirstgameprojectkotlin

import android.content.Context
import androidx.core.content.ContextCompat

//Missile снаряд
class Missile(context: Context, missileCaster: Player, positionX: Float, positionY: Float, radius: Float)
    :Circle(context, positionX, positionY, radius) {
    constructor(context: Context, missileCaster: Player) : this(
        context = context,
        missileCaster =missileCaster,
        positionX = missileCaster.getPositionX(),
        positionY = missileCaster.getPositionY(),
        radius = 30f
    )
companion object{
    private const val SPEED_PIXELS_PER_SECOND =900F
    private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS
}

    private var missileCaster : Player
    init {
        ContextCompat.getColor(context, R.color.Missile)
        this.missileCaster=missileCaster
        this.positionX= missileCaster.getPositionX()
        this.positionY= missileCaster.getPositionY()
        this.radius = 25f
        velocityX=missileCaster.getDirectionXinGame()*MAX_SPEED
        velocityY=missileCaster.getDirectionYinGame()*MAX_SPEED
    }


    override fun update() {
       positionX+=velocityX
       positionY+=velocityY
    }

}
