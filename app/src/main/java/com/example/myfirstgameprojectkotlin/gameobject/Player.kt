package com.example.myfirstgameprojectkotlin.gameobject

import android.content.Context
import android.graphics.Canvas
import com.example.myfirstgameprojectkotlin.GameDisplay
import com.example.myfirstgameprojectkotlin.GameLoop
import com.example.myfirstgameprojectkotlin.HealthBar
import com.example.myfirstgameprojectkotlin.Utils
import com.example.myfirstgameprojectkotlin.gameinterface.Joystick
import com.example.myfirstgameprojectkotlin.graphics.Animator
import com.example.myfirstgameprojectkotlin.graphics.Sprite
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet


class Player(
    context: Context?,
    private val joystick: Joystick,
    color:Int,
    positionX: Float,
    positionY: Float,
    radius: Float,
//    animator: Animator
//    spriteSheet: SpriteSheet
    sprite: Sprite
) :
    Circle(
        context!!,
        color,
        positionX,
        positionY,
        radius
    ) {
    companion object {
        const val SPEED_PIXELS_PER_SECOND = 400.0f
        private const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
        const val MAX_HEALTH_POINTS = 10
    }
    private val healthBar: HealthBar
    private var healthPoints = MAX_HEALTH_POINTS
    var sprite = sprite
//    private val animator: Animator
//    private val playerState: PlayerState

    init {
        healthBar = HealthBar(context!!, this)
//        this.animator = animator
//        playerState = PlayerState(this)
    }

    override fun update() {

        // Update velocity based on actuator of joystick
        velocityX = (joystick.getActuatorX() * MAX_SPEED).toFloat()
        velocityY = (joystick.getActuatorY() * MAX_SPEED).toFloat()

        // Update position
        positionX += velocityX
        positionY += velocityY

        // Update direction
        if (velocityX != 0f || velocityY != 0f) {
            val distance = Utils.getDistanceBetweenPoints(0f, 0f, velocityX, velocityY)
            directionXinGame = velocityX / distance
            directionYinGame = velocityY / distance
        }
//        playerState.update()
    }

    override fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
//        animator.draw(canvas, gameDisplay, this)
        sprite.draw(canvas,
            (gameDisplay.gameToDisplayCoordinatesX(getPositionX()).toInt()-64), //отнимаем размер половину
            gameDisplay.gameToDisplayCoordinatesY(getPositionY()).toInt()-64)
        healthBar.draw(canvas, gameDisplay)
    }

    fun getHealthPoints(): Int {
        return healthPoints
    }
    fun setHealthPoints(healthPoints: Int) {
        if(healthPoints>=0){
            this.healthPoints=healthPoints
        }
    }
//    fun getPlayerState(): PlayerState? {
//        return playerState
//    }

}
//
//class Player(context: Context, color:Int, joystick: Joystick, positionX: Float,
//             positionY: Float, radius : Float, animator: Animator)
//    : Circle(context,color, positionX,positionY,radius){
//    companion object {
//        const val SPEED_PIXELS_PER_SECOND =400F
//        const val MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS
//        var MAX_HEALTH_POINTS: Int = 10
//    }
//
//    private var context: Context
//    private var animator: Animator
//    private var healthPoints: Int
//    private var healthBar: HealthBar
//    private val joystick: Joystick
//    private var playerState : PlayerState = PlayerState(this)
//    init{
//        this.animator=animator
//        this.context=context
//        this.joystick=joystick
//        this.healthBar = HealthBar(context, this)
//        this.healthPoints = MAX_HEALTH_POINTS
//
//    }
//
//
//    override fun update() {
//        velocityX=joystick.getActuatorX()* MAX_SPEED
//        velocityY=joystick.getActuatorY()* MAX_SPEED
//        positionX+=velocityX
//        positionY+=velocityY
//        if (velocityX!=0f || velocityY!=0f){
//            var distance : Float  = Utils.getDistanceBetweenPoints(0f, 0f, velocityX, velocityY)
//            directionXinGame=velocityX/distance
//            directionYinGame=velocityY/distance
//        }
//        playerState.update()
//    }
//
// override fun draw(canvas: Canvas, gameDisplay: GameDisplay){
//     animator.draw(canvas, gameDisplay, this)
//     healthBar.draw(canvas,gameDisplay)
//}
//
//    fun getHealthPoints(): Int {
//        return healthPoints
//    }
//
//    fun setHealthPoints(healthPoints: Int) {
//        if(healthPoints>=0){
//            this.healthPoints=healthPoints
//        }
//
//    }
//
// fun getPlayerState() : PlayerState{
//     return playerState
// }
//}
//
//
//












