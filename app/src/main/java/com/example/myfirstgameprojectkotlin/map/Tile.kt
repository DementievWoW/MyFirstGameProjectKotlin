package com.example.myfirstgameprojectkotlin.map

import android.graphics.Canvas
import android.graphics.Rect
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet


abstract class Tile(mapLocationRect: Rect) {
    protected var mapLocationRect: Rect = mapLocationRect

    enum class TileType {
        GRASS_TILE,
        MANA_TILE
    }

    companion object{
        fun getTile(idxTileType: Int, spriteSheet: SpriteSheet, mapLocationRect: Rect): Tile? {
            return when (TileType.values()[idxTileType]) {
                TileType.GRASS_TILE -> {
                    GrassTile(spriteSheet, mapLocationRect)

                }
                TileType.MANA_TILE -> {
                    ManaTile(spriteSheet, mapLocationRect)

                }
                else -> null
            }
        }
    }
    abstract  fun draw(canvas: Canvas)

}