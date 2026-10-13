
package com.example.ejercicio1coursera

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val raiz = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(248, 250, 252))
        }

        val barra = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), 0, dp(16), 0)
            setBackgroundColor(Color.rgb(117, 226, 233))
        }

        val titulo = TextView(this).apply {
            text = "🐾  Petagram"
            textSize = 23f
            setTextColor(Color.rgb(77, 62, 66))
        }

        barra.addView(
            titulo,
            LinearLayout.LayoutParams(0, dp(60), 1f)
        )

        val estrella = TextView(this).apply {
            text = "★"
            textSize = 32f
            gravity = Gravity.CENTER
            contentDescription = "Abrir mascotas favoritas"
            setTextColor(Color.rgb(239, 83, 68))
            setOnClickListener {
                startActivity(
                    Intent(
                        this@MainActivity,
                        MascotasFavoritasActivity::class.java
                    )
                )
            }
        }

        barra.addView(
            estrella,
            LinearLayout.LayoutParams(dp(52), dp(60))
        )

        raiz.addView(barra)

        val area = FrameLayout(this)

        val lista = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MascotaAdapter(MascotasData.mascotas)
            setPadding(dp(14), dp(12), dp(14), dp(80))
            clipToPadding = false
        }

        area.addView(
            lista,
            FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        val subir = TextView(this).apply {
            text = "↑"
            textSize = 26f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            background = fondoRedondeado(
                Color.rgb(239, 83, 68),
                dp(30).toFloat()
            )
            elevation = dp(5).toFloat()
            contentDescription = "Subir al inicio"
            setOnClickListener {
                lista.smoothScrollToPosition(0)
            }
        }

        val parametros = FrameLayout.LayoutParams(
            dp(56), dp(56),
            Gravity.BOTTOM or Gravity.END
        ).apply {
            setMargins(0, 0, dp(22), dp(22))
        }

        area.addView(subir, parametros)

        raiz.addView(
            area,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(raiz)
    }
}
