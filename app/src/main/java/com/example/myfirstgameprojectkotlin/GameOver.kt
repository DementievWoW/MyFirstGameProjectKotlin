package com.example.myfirstgameprojectkotlin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat

class GameOver(context: Context) {
    private var context: Context
    private var paint: Paint = Paint()
    private val y: Float=800f
    private val x: Float=200f
    init {
        this.context =context
        paint.color=ContextCompat.getColor(context, R.color.gameOver)
        val textSize=150f
        paint.textSize=textSize
    }

    //сделаю вар, что бы динамически изменять надпись, от смерти
    private var text: String = "Game Over"

    fun draw(canvas: Canvas){
       canvas.drawText(text, x, y,paint)
   }
}
