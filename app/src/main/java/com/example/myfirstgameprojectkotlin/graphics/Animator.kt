package com.example.myfirstgameprojectkotlin.graphics

import android.graphics.Canvas
import com.example.myfirstgameprojectkotlin.GameDisplay
import com.example.myfirstgameprojectkotlin.gameobject.Player
import com.example.myfirstgameprojectkotlin.gameobject.PlayerState


class Animator(private val playerSpriteArray: Array<Sprite>) {
    private val idxNotMovingFrame = 0
    private var idxMovingFrame = 1
    private var updatesBeforeNextMoveFrame=5
    fun draw(canvas: Canvas?, gameDisplay: GameDisplay, player: Player) {
        drawFrame(
                    canvas, gameDisplay, player,playerSpriteArray[0])


//        when (player.getPlayerState()!!.getState()) {
//            PlayerState.State.NOT_MOVING -> playerSpriteArray[idxNotMovingFrame]?.let {
//                drawFrame(
//                    canvas, gameDisplay, player,
//                    it
//                )
//            }
//            PlayerState.State.START_MOVING -> {
//                updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
//                playerSpriteArray[idxMovingFrame]?.let {
//                    drawFrame(canvas, gameDisplay, player,
//                        it
//                    )
//                }
//            }
//            PlayerState.State.IS_MOVING -> {
//                updatesBeforeNextMoveFrame--
//                if (updatesBeforeNextMoveFrame == 0) {
//                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
//                    toggleIdxMovingFrame()
//                }
//                playerSpriteArray[idxMovingFrame]?.let {
//                    drawFrame(canvas, gameDisplay, player,
//                        it
//                    )
//                }
//            }
//            else -> {}
//        }
    }

    private fun toggleIdxMovingFrame() {
        idxMovingFrame = if (idxMovingFrame == 1) 2 else 1
    }

    private fun drawFrame(canvas: Canvas?, gameDisplay: GameDisplay, player: Player, sprite: Sprite) {
        sprite.draw(
            canvas!!,
            gameDisplay.gameToDisplayCoordinatesX(player.positionX).toInt() - sprite.getWidth() / 2,
            gameDisplay.gameToDisplayCoordinatesY(player.positionY).toInt() - sprite.getHeight() / 2
        )
    }

    companion object {
        private const val MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5
    }
}
//class Animator(private val playerSpriteArray: Array<Sprite?>) {
//    private val idxNotMovingFrame = 0
//    private var idxMovingFrame = 1
//    private var updatesBeforeNextMoveFrame = 0
//    fun draw(canvas: Canvas?, gameDisplay: GameDisplay, player: Player) {
//        when (player.getPlayerState()?.getState()) {
//            PlayerState.State.NOT_MOVING -> playerSpriteArray[idxNotMovingFrame]?.let {
//                drawFrame(
//                    canvas, gameDisplay, player,
//                    it
//                )
//            }
//            PlayerState.State.START_MOVING -> {
//                updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
//                playerSpriteArray[idxMovingFrame]?.let {
//                    drawFrame(canvas, gameDisplay, player,
//                        it
//                    )
//                }
//            }
//            PlayerState.State.IS_MOVING -> {
//                updatesBeforeNextMoveFrame--
//                if (updatesBeforeNextMoveFrame == 0) {
//                    updatesBeforeNextMoveFrame =
//                       MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME
//                    toggleIdxMovingFrame()
//                }
//                playerSpriteArray[idxMovingFrame]?.let {
//                    drawFrame(canvas, gameDisplay, player,
//                        it
//                    )
//                }
//            }
//            else -> {null}
//        }
//    }
//
//    private fun toggleIdxMovingFrame() {
//        idxMovingFrame = if (idxMovingFrame == 1) 2 else 1
//    }
//
//    private fun drawFrame(canvas: Canvas?, gameDisplay: GameDisplay, player: Player, sprite: Sprite) {
//        sprite.draw(
//            canvas!!,
//            gameDisplay.gameToDisplayCoordinatesX(player.positionX).toInt() - sprite.getWidth() / 2,
//            gameDisplay.gameToDisplayCoordinatesY(player.positionY).toInt() - sprite.getHeight() / 2
//        )
//    }
//
//    companion object {
//        private const val MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 5
//    }
//}
