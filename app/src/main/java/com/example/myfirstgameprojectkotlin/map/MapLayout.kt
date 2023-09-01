package com.example.myfirstgameprojectkotlin.map

import android.icu.text.CaseMap.Title

open class MapLayout {

    private lateinit var layout : Array<Array<Int>>



    companion object{

        var TILE_WIDTH_PIXELS=128
        var TILE_HEIGHT_PIXELS=128
        var NUMBER_OF_ROW_TILE=40
        var NUMBER_OF_COLUMN_TILE=40

    }

    init {
        initializeLayout()
    }
    fun getLayout():Array<Array<Int>>{
        return layout
    }

    private fun initializeLayout() {

    layout = Array(40) { Array(40) { 0 } }
    }
}
















