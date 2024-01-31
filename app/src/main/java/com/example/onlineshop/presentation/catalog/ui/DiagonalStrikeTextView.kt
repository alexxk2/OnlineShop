package com.example.onlineshop.presentation.catalog.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.onlineshop.R

class DiagonalStrikeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    private var dividerColor: Int = 0
    private var paint: Paint

    init {
        dividerColor = ContextCompat.getColor(context,R.color.grey)

         paint = Paint().apply {
            color = dividerColor
            strokeWidth = resources.getDimension(R.dimen.strike_line_width)
        }

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        val startY = height * 0.6f
        val stopY = height - startY
        canvas.drawLine(0.0f, startY, width.toFloat(), stopY, paint)
    }
}