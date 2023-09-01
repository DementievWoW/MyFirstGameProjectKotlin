package com.example.myfirstgameprojectkotlin.map

import android.graphics.Canvas
import android.graphics.Rect
import com.example.myfirstgameprojectkotlin.graphics.Sprite
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet

class GrassTile(spriteSheet: SpriteSheet, mapLocationRect: Rect) : Tile(mapLocationRect) {

    private var sprite: Sprite

    init {
        sprite = spriteSheet.getGrassSprite()
    }

    override fun draw(canvas: Canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top)
    }

}
