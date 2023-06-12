package com.example.myfirstgameprojectkotlin

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.view.KeyEvent.*
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


const val CELL_SIZE=200;
const val VERTICAL_CELL_AMOUNT=12;
const val HORZONTAL_CELL_AMOUNT=9;
const val VERTICAL_MAX_SIZE=CELL_SIZE*VERTICAL_CELL_AMOUNT
const val HORIZONTAL_MAX_SIZE=CELL_SIZE*HORZONTAL_CELL_AMOUNT




class MainActivity : AppCompatActivity() {
//    private val gridDrawer by lazy {
//        GridDrawer(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Game(this))
//        var hero=findViewById<ImageView>(R.id.myHero)
//        hero.layoutParams.height=CELL_SIZE
//        hero.layoutParams.width=CELL_SIZE
//        findViewById<FrameLayout>(R.id.container).layoutParams=FrameLayout.LayoutParams(HORIZONTAL_MAX_SIZE,VERTICAL_MAX_SIZE)
//        findViewById<FrameLayout>(R.id.user_interface).layoutParams=FrameLayout.LayoutParams(
//            Resources.getSystem().displayMetrics.heightPixels, Resources.getSystem().displayMetrics.widthPixels)
    }

}
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.settings,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId){
//            R.id.menu_settings->{
//                gridDrawer.drawGrid()
//                return true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        when(keyCode){
//            KEYCODE_DPAD_UP -> move(Direction.UP)
//            KEYCODE_DPAD_DOWN ->move(Direction.BOTTOM)
//            KEYCODE_DPAD_LEFT ->move(Direction.LEFT)
//            KEYCODE_DPAD_RIGHT ->move(Direction.RIGHT)
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//    private fun move(direction: Direction){
//
//        var myHero=findViewById<ImageView>(R.id.myHero)
//
//        when(direction){
//            Direction.UP ->(myHero.layoutParams as FrameLayout.LayoutParams).topMargin+=-CELL_SIZE;
//            Direction.BOTTOM ->(myHero.layoutParams as FrameLayout.LayoutParams).topMargin+=CELL_SIZE;
//            Direction.RIGHT -> (myHero.layoutParams as FrameLayout.LayoutParams).leftMargin+=CELL_SIZE;
//            Direction.LEFT -> (myHero.layoutParams as FrameLayout.LayoutParams).leftMargin-=CELL_SIZE;
//        }
//        findViewById<FrameLayout>(R.id.container).removeView(myHero)
//        findViewById<FrameLayout>(R.id.container).addView(myHero)
//
//    }
//
//    fun getMenu(view: View) {
//        if(this.supportActionBar==null){
//            this.supportActionBar!!.show()
//        }
//        else this.supportActionBar!!.hide()
//    }
//
//}