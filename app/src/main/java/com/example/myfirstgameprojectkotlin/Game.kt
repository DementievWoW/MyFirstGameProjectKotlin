package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat

class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private var player: Player
    private val gameLoop: GameLoop
    init {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        gameLoop = GameLoop(this, surfaceHolder)
        player = Player(getContext(),500F,500F,30F);
        isFocusable = true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
       when(event?.action){
           MotionEvent.ACTION_DOWN -> {
               player.setPosition(event.x, event.y)
               return true
           }
           MotionEvent.ACTION_MOVE->{
               player.setPosition(event.x, event.y)
               return true
           }

       }
        return super.onTouchEvent(event)
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        gameLoop.startLoop()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {}
    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {}
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawUPS(canvas)
        drawFPS(canvas)
        player.draw(canvas)
    }

    private fun drawUPS(canvas: Canvas) {
        val averageUPS = gameLoop.averageUPS.toString()
        val paint = Paint()
        val color = ContextCompat.getColor(context, R.color.purple_200)
        paint.color = color
        paint.textSize = 50f
        canvas.drawText("UPS: $averageUPS", 100f, 100f, paint)
    }

    private fun drawFPS(canvas: Canvas) {
        val averageFPS = gameLoop.averageFPS.toString()
        val paint = Paint()
        val color = ContextCompat.getColor(context, R.color.purple_200)
        paint.color = color
        paint.textSize = 50f
        canvas.drawText("FPS: $averageFPS", 100f, 200f, paint)
    }

    fun update() {
        player.update()
    }
}