package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.myfirstgameprojectkotlin.gameobject.Player


//на экране отображается хп
class HealthBar(context: Context, player: Player) {
    private var width: Int
    private var player: Player
    private var height: Int
    private var margin: Byte
    private var borderPaint: Paint
    private var healthPaint : Paint

    init {
        this.player=player
        this.width=100
        this.height=20
        this.margin=2
        this.borderPaint = Paint()
        this.healthPaint = Paint()
        var borderColor : Int =ContextCompat.getColor(context, R.color.healBarBorder)
        var healthColor : Int =ContextCompat.getColor(context, R.color.healBarHealth)
        borderPaint.color=borderColor
        healthPaint.color=healthColor

    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay){
        var x: Float=player.getPositionX()
        var y: Float=player.getPositionY()
        var distanceToPlayer =60f
        var healthPointHero : Float=player.getHealthPoints().toFloat()/ Player.MAX_HEALTH_POINTS
        var borderStart = x - width/2
        var borderEnd = x+width/2
        var borderBottom = y - distanceToPlayer
        var borderTop =borderBottom-height
        canvas.drawRect(
            gameDisplay.gameToDisplayCoordinatesX(borderStart),
            gameDisplay.gameToDisplayCoordinatesY(borderTop),
            gameDisplay.gameToDisplayCoordinatesX(borderEnd),
            gameDisplay.gameToDisplayCoordinatesY(borderBottom),
            borderPaint )
        var healthWidth = width- 2*margin
        var healthHeight = height -2*margin
        var healthStart = borderStart+margin
        var healthEnd = healthStart +healthWidth*healthPointHero
        var healthBottom = borderBottom-margin
        var healthTop = healthBottom - healthHeight
        canvas.drawRect(
            gameDisplay.gameToDisplayCoordinatesX(healthStart),
            gameDisplay.gameToDisplayCoordinatesY(healthTop),
            gameDisplay.gameToDisplayCoordinatesX(healthEnd),
            gameDisplay.gameToDisplayCoordinatesY(healthBottom),
            healthPaint )
    }
}






















