package com.example.myfirstgameprojectkotlin.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.example.myfirstgameprojectkotlin.GameDisplay
import com.example.myfirstgameprojectkotlin.graphics.SpriteSheet

class Tilemap(spriteSheet: SpriteSheet) {
    private lateinit var mapBitmap: Bitmap
    private val spriteSheet: SpriteSheet = spriteSheet
    private lateinit var tilemap: Array<Array<Tile?>>
    private var mapLayout: MapLayout

    init {
        mapLayout = MapLayout()
        initializeTilemap()

    }


    private fun initializeTilemap() {
        var layout :Array<Array<Int>> = mapLayout.getLayout()

//        tilemap = Array(MapLayout.NUMBER_OF_ROW_TILE){arrayOfNulls<Tile?>{MapLayout.NUMBER_OF_COLUMN_TILE} }
        tilemap = Array<Array<Tile?>>(MapLayout.NUMBER_OF_COLUMN_TILE) {
            arrayOfNulls<Tile>(
                MapLayout.NUMBER_OF_COLUMN_TILE
            )}
        for (iRow in 0 until MapLayout.NUMBER_OF_ROW_TILE) {
            for (iCol in 0 until MapLayout.NUMBER_OF_COLUMN_TILE) {
                tilemap[iRow][iCol] = Tile.getTile(
                    layout[iRow][iCol],
                    spriteSheet,
                    getRectByIndex(iRow, iCol)


                )!!

            }
        }
        val config : Bitmap.Config = Bitmap.Config.ARGB_8888
        mapBitmap = Bitmap.createBitmap(
            MapLayout.NUMBER_OF_COLUMN_TILE*MapLayout.TILE_WIDTH_PIXELS,
            MapLayout.NUMBER_OF_ROW_TILE*MapLayout.TILE_HEIGHT_PIXELS,
            config

        )
        var mapCanvas : Canvas = Canvas(mapBitmap)
        for (iRow in 0 until MapLayout.NUMBER_OF_ROW_TILE) {
            for (iCol in 0 until MapLayout.NUMBER_OF_COLUMN_TILE) {
                tilemap[iRow][iCol]?.draw(mapCanvas)

            }
        }
//        System.out.println()
    }

    private fun getRectByIndex(idxRow: Int, idxCol: Int): Rect {
      return  Rect(
            idxCol* MapLayout.TILE_WIDTH_PIXELS,
            idxRow* MapLayout.TILE_HEIGHT_PIXELS,
          MapLayout.TILE_WIDTH_PIXELS *(idxCol+1),
          MapLayout.TILE_HEIGHT_PIXELS *(idxRow+1))
    }

    fun draw(canvas: Canvas, gameDisplay: GameDisplay) {
        canvas.drawBitmap(
            mapBitmap,
            gameDisplay.getGameRect(),
            gameDisplay.DISPLAY_RECT,
            null
        )
    }
}
















