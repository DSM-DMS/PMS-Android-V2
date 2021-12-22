package com.dms.pmsandroid.presentation.feature.calendar.ui.decorator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import androidx.core.content.ContextCompat
import com.dms.pmsandroid.R
import com.dms.pmsandroid.data.calendar.EventTypes

class CustomMultipleDotSpan(private val eventTypes: List<EventTypes>, context: Context) : LineBackgroundSpan {

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
        val total = if (eventTypes.size > 3) 3 else eventTypes.size
        var leftMost = (total - 1) * -12

        for (i in 0 until total) {
            val oldColor = paint.color
            when (eventTypes[i]) {
                EventTypes.MUST_GO_HOME -> {
                    paint.color = green
                }
                EventTypes.HOLIDAYS->{
                    paint.color = red
                }
                EventTypes.ETC->{
                    paint.color = blue
                }
                EventTypes.VACATION->{
                    paint.color = yellow
                }
                EventTypes.EXAM->{
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