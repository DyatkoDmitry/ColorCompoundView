package com.example.colorcompoundview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ColorPick @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var currentColor: Int = 0
    var colors:IntArray = context.resources.getIntArray(R.array.pick_colors)

    init{
        LayoutInflater.from(context).inflate(R.layout.color_pick,this,true)

        setButtonsListeners()

        showCurrentColor()

        val hex = "#" + Integer.toHexString(colors[currentColor]).toUpperCase().substring(2)
        findViewById<TextView>(R.id.hex).setText(hex)

    }

    private fun showCurrentColor(){
        findViewById<View>(R.id.selected_color).setBackgroundColor(colors[currentColor])
    }

    private fun showHex(){
        val hex = "#" + Integer.toHexString(colors[currentColor]).toUpperCase().substring(2)
        findViewById<TextView>(R.id.hex).setText(hex)
    }

    private fun setButtonsListeners(){
        findViewById<Button>(R.id.next).setOnClickListener {
            currentColor++
            if (currentColor == colors.size) currentColor = 0
            findViewById<View>(R.id.selected_color).setBackgroundColor(colors[currentColor])
            showHex()
        }

        findViewById<Button>(R.id.previous).setOnClickListener {
            currentColor--
            if (currentColor == -1) currentColor = colors.size - 1
            findViewById<View>(R.id.selected_color).setBackgroundColor(colors[currentColor])
            showHex()
        }
    }

    fun getCurrentColor():Int = colors[currentColor]

    fun getNextColor():Int{
        findViewById<Button>(R.id.next).performClick()
        return colors[currentColor]
    }

    fun getPreviousColor():Int{
        findViewById<Button>(R.id.previous).performClick()
        return colors[currentColor]
    }
}

