package com.example.onlineshop.presentation.details.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.onlineshop.R

class StatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.stats_item, this)
    }

    fun setStatName(name: String){
        val textView: TextView = findViewById(R.id.tv_stats_name)
        textView.text = name
    }

    fun setStatValue(value: String){
        val textView: TextView = findViewById(R.id.tv_stats_value)
        textView.text = value
    }
}