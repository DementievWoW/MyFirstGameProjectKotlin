package com.example.myfirstgameprojectkotlin

import kotlin.math.pow
import kotlin.math.sqrt

class Utils {
    companion object {
        fun getDistanceBetweenPoints(p1x: Float, p1y: Float, p2x: Float, p2y: Float): Float {
            return sqrt(
                (p1x - p2x).toDouble().pow(2.0)
                        +
                        (p1y - p2y).toDouble().pow(2.0)
            ).toFloat()
        }
    }

}
