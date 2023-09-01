package com.example.myfirstgameprojectkotlin

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        gameView = GameView(this)
        setContentView(gameView)
    }

    override fun onStart() {
        Log.d("MainActivity.kotlin","onStart()")
        super.onStart()
    }
    override fun onPause() {
        Log.d("MainActivity.kotlin","onResume()")
        gameView.pause() // также нужно в onDestroy() вызывать
        super.onPause()
    }
    override fun onResume() {
        Log.d("MainActivity.kotlin","onResume()")
        super.onResume()
    }
    override fun onStop() {
        Log.d("MainActivity.kotlin","onStop()")
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity.kotlin","onDestroy()")
    }

//    @MainThread
//    override fun onBackPressed() {
////        onBackPressedDispatcher.onBackPressed()
//    }
}
