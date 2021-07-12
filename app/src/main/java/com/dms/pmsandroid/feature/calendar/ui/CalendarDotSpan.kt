package com.dms.pmsandroid.feature.calendar.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class CalendarDotSpan:LineBackgroundSpan {
    private val radius:Float = 1F
    private var color = intArrayOf(Color.BLUE,Color.RED,Color.GREEN)

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

    }
}