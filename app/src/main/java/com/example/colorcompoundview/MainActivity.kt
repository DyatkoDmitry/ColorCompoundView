package com.example.colorcompoundview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pick = findViewById<ColorPick>(R.id.colorPick)
        pick.getNextColor()
        pick.getNextColor()
        pick.getNextColor()
        pick.getNextColor()
        pick.getPreviousColor()
    }
}