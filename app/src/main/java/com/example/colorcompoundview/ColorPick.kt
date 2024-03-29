package com.example.colorcompoundview

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
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
        updateState()
    }

    private fun updateState(){
        showCurrentColor()
        showHex()
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
            updateState()
        }

        findViewById<Button>(R.id.previous).setOnClickListener {
            currentColor--
            if (currentColor == -1) currentColor = colors.size - 1
            updateState()
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

    override fun onSaveInstanceState(): Parcelable? {
        return Bundle().apply{
            putInt(AppConst.CURRENT_COLOR, currentColor)
            putParcelable(AppConst.SUPER_STATE, super.onSaveInstanceState())
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle){
            currentColor = state.getInt(AppConst.CURRENT_COLOR)
            super.onRestoreInstanceState(state.getParcelable(AppConst.SUPER_STATE))
        } else {
            super.onRestoreInstanceState(state)
        }
        updateState()
    }
}

