package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class CustomMultipleDotSpan : LineBackgroundSpan {

    private val radius = 8F
    private val dotColor = arrayListOf(
        Color.BLUE,
        Color.RED,
        Color.GREEN
    )

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lineNumber: Int
    ) {
        val total = 3
        var leftMost = (total - 1) * -12

        for(i in 0 until total){
            val oldColor = paint.color
            paint.color = dotColor[i]
            canvas.drawCircle(((left + right)/2 - leftMost).toFloat(),bottom + radius, radius, paint)
            paint.color = oldColor
            leftMost += 24
        }
    }
}