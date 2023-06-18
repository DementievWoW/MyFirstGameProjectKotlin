package com.example.myfirstgameprojectkotlin.gameobject

class PlayerState(player: Player) {
    enum class State {
        NOT_MOVING,
        START_MOVING,
        IS_MOVING
    }
    fun getState() : State{
        return state
    }

    private var player: Player = player
    private var state: State = State.NOT_MOVING
    fun update(){
        when(state){
            State.NOT_MOVING -> {
                if(player.velocityX !=0f || player.velocityY!=0f){
                    state = State.START_MOVING
                }
            }
            State.START_MOVING -> {
                if(player.velocityX !=0f || player.velocityY!=0f){
                    state = State.IS_MOVING
                }
            }
            State.IS_MOVING -> {
                if(player.velocityX ==0f && player.velocityY==0f){
                    state = State.NOT_MOVING
                }
            }
        }
    }
}