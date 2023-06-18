package com.example.myfirstgameprojectkotlin.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.myfirstgameprojectkotlin.R

class SpriteSheet(context: Context) {
    companion object{
        private const val SPRITE_WIDTH_PIXELS = 64
        private const val SPRITE_HEIGHT_PIXELS = 64
    }
    private var bitmap: Bitmap
    init {
        var bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inScaled=false
        this.bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sprite_sheet, bitmapOptions)
    }

    fun getPlayerSpriteArray(): Array<Sprite> {
        return arrayOf(
            Sprite(this, Rect(0 * 64, 0, 0 * 64, 64)),
            Sprite(this, Rect(1 * 64, 0, 1 * 64, 64)),
            Sprite(this, Rect(2 * 64, 0, 2 * 64, 64))
        )
    }

    fun getBitmap(): Bitmap {
    return bitmap
    }



    private fun getSpriteByIndex(idxRow: Int, idxCol: Int): Sprite {
        return Sprite(this, Rect(
            idxCol*SPRITE_WIDTH_PIXELS,
            idxRow*SPRITE_HEIGHT_PIXELS,
            SPRITE_WIDTH_PIXELS*(idxCol+1),
            SPRITE_HEIGHT_PIXELS*(idxRow+1)
        ))
    }
    fun getGrassSprite(): Sprite {
        return getSpriteByIndex(1,0)

    }
//
    fun getManaSprite(): Sprite {
        return getSpriteByIndex(1,1)

    }
}