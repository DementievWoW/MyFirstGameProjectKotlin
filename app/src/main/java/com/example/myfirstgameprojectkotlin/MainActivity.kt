package com.example.myfirstgameprojectkotlin

import android.os.Bundle
import android.util.Log
import androidx.annotation.MainThread

import androidx.appcompat.app.AppCompatActivity




class MainActivity : AppCompatActivity() {

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        game = Game(this)
        setContentView(game)
    }

    override fun onStart() {
        Log.d("MainActivity.kotlin","onStart()")
        super.onStart()
    }
    override fun onPause() {
        Log.d("MainActivity.kotlin","onResume()")
        game.pause()
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
