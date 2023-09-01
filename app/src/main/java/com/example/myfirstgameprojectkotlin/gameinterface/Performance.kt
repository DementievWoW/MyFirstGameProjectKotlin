package com.example.myfirstgameprojectkotlin.gameinterface

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.example.myfirstgameprojectkotlin.GameLoop
import com.example.myfirstgameprojectkotlin.R

//представления, которые выводяться для пользователя
class Performance(context: Context, gameLoop: GameLoop) {

    private val context: Context = context
    private val gameLoop: GameLoop = gameLoop

    fun draw(canvas: Canvas){
        drawUPS(canvas)
        drawFPS(canvas)
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

}
