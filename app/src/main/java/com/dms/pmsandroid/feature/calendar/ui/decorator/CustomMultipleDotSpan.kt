package com.dms.pmsandroid.feature.calendar.ui.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R

class CustomMultipleDotSpan(private val dots: List<Int>, context: Context) : LineBackgroundSpan {

    private val radius = 10F

    private val green = ContextCompat.getColor(context, R.color.green)
    private val red = ContextCompat.getColor(context, R.color.red)
    private val yellow = ContextCompat.getColor(context, R.color.yellow)
    private val blue = ContextCompat.getColor(context, R.color.blue)
    private val purple = ContextCompat.getColor(context, R.color.purple)

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
        val total = if (dots.size > 3) 3 else dots.size
        var leftMost = (total - 1) * -12

        for (i in 0 until total) {
            val oldColor = paint.color
            when (dots[i]) {
                Color.GREEN -> {
                    paint.color = green
                }
                Color.RED->{
                    paint.color = red
                }
                Color.GRAY->{
                    paint.color = red
                }
                Color.BLUE->{
                    paint.color = blue
                }
                Color.YELLOW->{
                    paint.color = yellow
                }
                else->{
                    paint.color = purple
                }

            }
            canvas.drawCircle(
                ((left + right) / 2 - leftMost).toFloat(),
                bottom + radius,
                radius,
                paint
            )
            paint.color = oldColor
            leftMost += 24
        }
    }
}