package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat
import com.example.myfirstgameprojectkotlin.gameinterface.GameOver
import com.example.myfirstgameprojectkotlin.gameinterface.Joystick
import com.example.myfirstgameprojectkotlin.gameinterface.Performance
import com.example.myfirstgameprojectkotlin.gameobject.Circle
import com.example.myfirstgameprojectkotlin.gameobject.Enemy
import com.example.myfirstgameprojectkotlin.gameobject.Missile
import com.example.myfirstgameprojectkotlin.gameobject.Player
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet
import com.example.myfirstgameprojectkotlin.map.Tilemap

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private val spriteSheet: SpriteSheet = SpriteSheet(context)
    private var gameOver: GameOver = GameOver(context)
    private var numberOfSpellsToCast: Int = 0
    private var joystickPointerId: Int = 0
    private val joystick: Joystick = Joystick()
    private val player: Player = Player(
        context = context,
        joystick = joystick,
        color = ContextCompat.getColor(context, R.color.Player),
        sprite = spriteSheet.getPlayerSprite()
    )
    private var gameTask: GameTask = GameTask(this, holder)
    private var performance: Performance = Performance(context, gameTask)
    private var enemies: MutableList<Enemy> = mutableListOf()
    private var missiles: MutableList<Missile> = mutableListOf()
    private var tileMap: Tilemap = Tilemap(spriteSheet)
    private var gameDisplay: GameDisplay = GameDisplay(
        widthPixels = Resources.getSystem().displayMetrics.widthPixels,
        heightPixels = Resources.getSystem().displayMetrics.heightPixels,
        centerObject = player
    )

    init {
        holder.addCallback(this)
        isFocusable = true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    if (joystick.getIsPressed())
                        numberOfSpellsToCast++
                    else if (joystick.isPressed(event.x, event.y)) {
                        joystickPointerId = event.getPointerId(event.actionIndex)
                        joystick.setIsPressed(true)
                    }
                    return true
                }

                MotionEvent.ACTION_MOVE -> {
                    if (joystick.getIsPressed()) {
                        joystick.setActuator(event.x, event.y)
                    }
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    if (joystickPointerId == event.getPointerId(event.actionIndex)) {
                        joystick.setIsPressed(false)
                        joystick.resetActuator()
                    }
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        Log.d("Game.kotlin", "surfaceCreated()")
        if (gameTask.state.equals(Thread.State.TERMINATED)) {
            gameTask = GameTask(this, holder)
        }
        gameTask.startLoop()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        Log.d("Game.kotlin", "surfaceChanged()")
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        Log.d("Game.kotlin", "surfaceDestroyed()")
        // нужно вызывать GameTask.stopLoop()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        tileMap.draw(canvas, gameDisplay)
        player.draw(canvas, gameDisplay)
        enemies.forEach { it.draw(canvas, gameDisplay) }
        missiles.forEach { it.draw(canvas, gameDisplay) }
        if (player.getHealthPoints() <= 0) {
            gameOver.draw(canvas)
        }
        performance.draw(canvas)
        joystick.draw(canvas)
    }

    fun update() {
        if (player.getHealthPoints() <= 0) {
            return
        }
        joystick.update()
        player.update()
        if (Enemy.readyToSpawn()) {
            enemies.add(Enemy(context, player))
        }
        enemies.forEach { it.update() }

        while (numberOfSpellsToCast > 0) {
            missiles.add(Missile(context, player))
            numberOfSpellsToCast--
        }
        missiles.forEach { it.update() }
        //проверяем все взаимодействия врагов и снарядов
        // вынести в отдельную функцию, в которую нужно передавать два списка (снаряды и враги)
        // нужно найти пересечение списков, в соответствии с этим уменьшать hp и количество врагов
        val iteratorEnemy = enemies.iterator()
        while (iteratorEnemy.hasNext()) {
            val enemy: Circle = iteratorEnemy.next()
            if (Circle.isColliding(enemy, player)) {
                //далить врага и снять хп
                iteratorEnemy.remove()
                player.setHealthPoints(player.getHealthPoints() - 1)
                continue
            }
            var iteratorMissile = missiles.iterator()
            while (iteratorMissile.hasNext()) {
                var missile: Missile = iteratorMissile.next()
                //удалить заклинание, если оно столкнется с врагом
                if (Circle.isColliding(missile, enemy)) {
                    iteratorMissile.remove()
                    iteratorEnemy.remove()
                    break
                }
            }
        }
        gameDisplay.update()
    }

    fun pause() {
        gameTask.stopLoop()
    }
}












