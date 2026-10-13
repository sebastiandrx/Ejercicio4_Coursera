
package com.example.ejercicio1coursera

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MascotasFavoritasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val raiz = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.rgb(248, 250, 252))
        }

        val barra = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setBackgroundColor(Color.rgb(117, 226, 233))
            setPadding(dp(12), 0, dp(12), 0)
        }

        val regresar = TextView(this).apply {
            text = "←"
            textSize = 30f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(77, 62, 66))
            contentDescription = "Regresar a mascotas"
            setOnClickListener { finish() }
        }

        barra.addView(
            regresar,
            LinearLayout.LayoutParams(dp(48), dp(60))
        )

        val titulo = TextView(this).apply {
            text = "★  Mascotas favoritas"
            textSize = 21f
            setTextColor(Color.rgb(77, 62, 66))
        }

        barra.addView(titulo)

        raiz.addView(barra)

        val lista = RecyclerView(this).apply {
            layoutManager = LinearLayoutManager(
                this@MascotasFavoritasActivity
            )

            adapter = MascotaAdapter(
                MascotasData.favoritasDummy,
                permitirVotos = false
            )

            setPadding(dp(14), dp(12), dp(14), dp(20))
            clipToPadding = false
        }

        raiz.addView(
            lista,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(raiz)
    }
}
