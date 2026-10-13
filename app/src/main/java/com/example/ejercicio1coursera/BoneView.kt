
package com.example.ejercicio1coursera

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class BoneView(
    context: Context,
    private val boneColor: Int = Color.WHITE
) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = boneColor
        style = Paint.Style.FILL
    }

    private val outline = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        style = Paint.Style.STROKE
        strokeWidth = 1.8f
    }

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        val size = (36 * resources.displayMetrics.density).toInt()
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        canvas.translate(width / 2f, height / 2f)
        canvas.rotate(-35f)

        val scale = width / 36f
        canvas.scale(scale, scale)

        fun dibujarHueso(p: Paint) {
            canvas.drawRoundRect(
                -11f, -5f, 11f, 5f, 3f, 3f, p
            )
            canvas.drawCircle(-11f, -5f, 5f, p)
            canvas.drawCircle(-11f, 5f, 5f, p)
            canvas.drawCircle(11f, -5f, 5f, p)
            canvas.drawCircle(11f, 5f, 5f, p)
        }

        dibujarHueso(paint)

        if (boneColor == Color.WHITE) {
            dibujarHueso(outline)
        }

        canvas.restore()
    }
}
