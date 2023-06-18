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
import com.example.myfirstgameprojectkotlin.graphics.Animator
import com.example.myfirstgameprojectkotlin.graphics.Sprite
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet
import com.example.myfirstgameprojectkotlin.map.Tilemap

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private var animator: Animator
    private var tilemap: Tilemap
    private val spriteSheet: SpriteSheet
    private var gameDisplay: GameDisplay
    private var performance: Performance
    private var gameOver: GameOver
    private var numberOfSpellsToCast: Int=0
    private var joystickPointerId: Int=0
    private val joystick: Joystick
    private val player: Player
    private var gameLoop: GameLoop
    private var enemyList : MutableList<Enemy> = mutableListOf()
    private var missileList : MutableList<Missile> = mutableListOf()

    init {
        //Surface and callback
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        this.gameLoop = GameLoop(this, surfaceHolder)

        //инициализация интерфейсов
        this.performance=Performance(context,gameLoop)
        this.gameOver = GameOver(context)
        this.joystick = Joystick(475f,600f,40f,20f)
        //инициализация игровых обьектов
        this.spriteSheet=SpriteSheet(context)
        animator = Animator(spriteSheet.getPlayerSpriteArray())
        this.player = Player(
            getContext(),ContextCompat.getColor(context, R.color.Player),joystick,
            500F,500F,32F, animator

        )
        //инициализируем геймдисплей и центрируем вокруг игрока
                gameDisplay = GameDisplay(Resources.getSystem().displayMetrics.widthPixels,
                    Resources.getSystem().displayMetrics.heightPixels,
                    player)
        //инициализация карты
        tilemap= Tilemap(spriteSheet)
        isFocusable = true
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when(event.actionMasked){
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {

                    if (joystick.getIsPressed()){
                       numberOfSpellsToCast++
                    }
                   else if(joystick.isPressed(event.x, event.y)){
                       joystickPointerId=event.getPointerId(event.actionIndex)
                        joystick.setIsPressed(true)
                    }
                    else{
                        numberOfSpellsToCast++
                    }
                    return true
                }
                MotionEvent.ACTION_MOVE->{
                    if (joystick.getIsPressed()){
                        joystick.setActuator(event.x, event.y)
                    }
                    return true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_DOWN ->{
                    if(joystickPointerId==event.getPointerId(event.actionIndex)){
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
        Log.d("Game.kotlin","surfaceCreated()")

        //когда поток завершен нужно создать новый и никак иначе
        if (gameLoop.state.equals(Thread.State.TERMINATED)){

            gameLoop= GameLoop(this, holder)
        }
        gameLoop.startLoop()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        Log.d("Game.kotlin","surfaceChanged()")
    }
    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        Log.d("Game.kotlin","surfaceDestroyed()")
    }
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
            //нарисовать тайлы
        tilemap.draw(canvas,gameDisplay)
            //рисуем игровые обьекты
        player.draw(canvas, gameDisplay)
        for (enemy : Enemy in enemyList){
            enemy.draw(canvas, gameDisplay)
        }
        for(missile : Missile in missileList ){
            missile.draw(canvas, gameDisplay)
        }
        // если hp<=0 gameOver!
        if (player.getHealthPoints()<=0){
            gameOver.draw(canvas)
        }
        //рисуем игровой интерфейс
        performance.draw(canvas)
        joystick.draw(canvas)
    }

    fun update() {
        //остановка обновления, когда игрок умер
        if(player.getHealthPoints()<=0){
            return
        }
        //обновление состояний
        joystick.update()
        player.update()
        if (Enemy.readyToSpawn()){
            enemyList.add(Enemy(context,player))
        }
        for(enemy: Enemy in enemyList ){
                enemy.update()
        }
        while (numberOfSpellsToCast>0){
            missileList.add(Missile(context,player))
            numberOfSpellsToCast--
        }
        for(missile : Missile in missileList ){
            missile.update()
        }
            //проверяем все взаимодействия врагов и снарядов
        var iteratorEnemy = enemyList.iterator()
        while (iteratorEnemy.hasNext()){
            var enemy: Circle =iteratorEnemy.next()
            if(Circle.isColliding(enemy, player)){
                //далить врага и снять хп
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints()-1)
                continue
            }
            var iteratorMissile = missileList.iterator()
            while (iteratorMissile.hasNext()){
                var missile : Missile =  iteratorMissile.next()
                //удалить заклинание, если оно столкнется с врагом
                if (Circle.isColliding(missile, enemy)){
                    iteratorMissile.remove()
                    iteratorEnemy.remove()
                    break
                }
            }

        }
        gameDisplay.update()

    }

    fun pause() {
        gameLoop.stopLoop()
    }
}












