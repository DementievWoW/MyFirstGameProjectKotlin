package com.example.myfirstgameprojectkotlin.gameobject

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myfirstgameprojectkotlin.GameTask
import com.example.myfirstgameprojectkotlin.R


class Enemy(
    context: Context,
    color: Int,
    player: Player,
    positionX: Float,
    positionY: Float,
    radius: Float
) : Circle(context, color, positionX, positionY, radius) {
    constructor(context: Context, player: Player) : this(
        context = context,
        player = player,
        color = ContextCompat.getColor(context, R.color.Enemy),
        positionX = (Math.random() * 1000).toFloat(),
        positionY = (Math.random() * 1000).toFloat(),
        radius = 30f
    )

    companion object {
        fun readyToSpawn(): Boolean {
            return if (updatesUntilNextSpawn <= 0) {
                updatesUntilNextSpawn += UPDATES_PER_SPAWN
                true
            } else {
                updatesUntilNextSpawn--
                false
            }
        }

        private const val SPAWNS_PER_MINUTE: Float = 20f
        private const val SPAWNS_PER_SECOND: Float = SPAWNS_PER_MINUTE / 60
        private const val UPDATES_PER_SPAWN: Float = GameTask.MAX_UPS / SPAWNS_PER_SECOND
        private var updatesUntilNextSpawn: Float = UPDATES_PER_SPAWN
        private const val SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.7f
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameTask.MAX_UPS
    }

    private var distanceToPlayer: Float = 0.0f
    private var distanceToPlayerY: Float = 0.0f
    private var distanceToPlayerX: Float = 0.0f
    private val player: Player

    init {
        this.player = player
    }

    // Проверяет попадание во врага
    fun check(x: Float, y: Float) {

    }


    override fun update() {
        distanceToPlayerX = player.getPositionX() - positionX
        distanceToPlayerY = player.getPositionY() - positionY
        distanceToPlayer = getDistanceBetweenObject(this, player)
        var directionX4Player = distanceToPlayerX / distanceToPlayer
        var directionY4Player = distanceToPlayerY / distanceToPlayer
        if (distanceToPlayer > 0) {
            velocityX = directionX4Player * MAX_SPEED
            velocityY = directionY4Player * MAX_SPEED
        } else {
            velocityX = 0.0f
            velocityY = 0.0f
        }
        positionX += velocityX
        positionY += velocityY

    }

}


