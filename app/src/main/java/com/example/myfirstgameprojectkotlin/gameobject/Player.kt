package com.example.myfirstgameprojectkotlin.gameobject

import android.content.Context
import android.graphics.Canvas
import com.example.myfirstgameprojectkotlin.GameDisplay
import com.example.myfirstgameprojectkotlin.GameTask
import com.example.myfirstgameprojectkotlin.HealthBar
import com.example.myfirstgameprojectkotlin.Utils
import com.example.myfirstgameprojectkotlin.gameinterface.Joystick
import com.example.myfirstgameprojectkotlin.graphics.Sprite


class Player(
    context: Context,
    private val joystick: Joystick,
    private var missiles: Int = 0,
    color: Int,
    positionX: Float = 600F,
    positionY: Float = 600f,
    radius: Float = 64f,
    private var sprite: Sprite
) : Circle(
    context,
    color,
    positionX,
    positionY,
    radius
) {
    companion object {
        const val SPEED_PIXELS_PER_SECOND = 400.0f
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameTask.MAX_UPS
        const val MAX_HEALTH_POINTS = 10
    }

    private val healthBar: HealthBar
    private var healthPoints = MAX_HEALTH_POINTS

    init {
        healthBar = HealthBar(context, this) // инициализация сразу
    }

    override fun update() {

        // Update velocity based on actuator of joystick- в отдельную функцию
        velocityX = (joystick.getActuatorX() * MAX_SPEED)
        velocityY = (joystick.getActuatorY() * MAX_SPEED)

        // Update position - в отдельную функцию
        positionX += velocityX
        positionY += velocityY

        // Update direction - в отдельную функцию
        if (velocityX != 0f || velocityY != 0f) {
            val distance = Utils.getDistanceBetweenPoints(0f, 0f, velocityX, velocityY)
            directionXinGame = velocityX / distance
            directionYinGame = velocityY / distance
        }
    }

    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        sprite.draw(
            canvas = canvas,
            x = (gameDisplay.gameToDisplayCoordinatesX(getPositionX()).toInt() - 64), // в константу
            y = gameDisplay.gameToDisplayCoordinatesY(getPositionY()).toInt() - 64 // в константу
        )
        healthBar.draw(canvas, gameDisplay)
    }

    fun getHealthPoints(): Int = healthPoints

    fun setHealthPoints(healthPoints: Int) {
        if (healthPoints >= 0) {
            this.healthPoints = healthPoints
        }
    }
}