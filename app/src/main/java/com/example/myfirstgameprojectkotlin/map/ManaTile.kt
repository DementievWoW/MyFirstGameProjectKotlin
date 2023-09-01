package com.example.myfirstgameprojectkotlin.map

import android.graphics.Canvas
import android.graphics.Rect
import com.example.myfirstgameprojectkotlin.graphics.Sprite
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet

class ManaTile(spriteSheet: SpriteSheet, mapLocationRect: Rect) : Tile(mapLocationRect) {

    private var grassSprite: Sprite
    private var manaSprite: Sprite

    init {
        grassSprite = spriteSheet.getGrassSprite()
        manaSprite = spriteSheet.getManaSprite()
    }

    override fun draw(canvas: Canvas) {

        grassSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
        manaSprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
    }
}
