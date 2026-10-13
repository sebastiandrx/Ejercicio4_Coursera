
package com.example.ejercicio1coursera

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val raiz = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
        }

        val barra = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), 0, dp(12), 0)
            setBackgroundColor(Color.rgb(117, 226, 233))
        }

        val titulo = TextView(this).apply {
            text = "🐾 Petagram"
            textSize = 23f
            setTextColor(Color.rgb(77, 62, 66))
        }

        barra.addView(
            titulo,
            LinearLayout.LayoutParams(0, dp(60), 1f)
        )

        val estrella = TextView(this).apply {
            text = "★"
            textSize = 30f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(239, 83, 68))
            contentDescription = "Mascotas favoritas"

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
            LinearLayout.LayoutParams(dp(48), dp(60))
        )

        val menu = TextView(this).apply {
            text = "⋮"
            textSize = 30f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(77, 62, 66))
            contentDescription = "Menú de opciones"

            setOnClickListener {
                val popup = PopupMenu(this@MainActivity, this)

                popup.menu.add("Contacto")
                popup.menu.add("Acerca De")

                popup.setOnMenuItemClickListener { item ->
                    when (item.title.toString()) {
                        "Contacto" -> {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    ContactoActivity::class.java
                                )
                            )
                            true
                        }

                        "Acerca De" -> {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    AcercaDeActivity::class.java
                                )
                            )
                            true
                        }

                        else -> false
                    }
                }

                popup.show()
            }
        }

        barra.addView(
            menu,
            LinearLayout.LayoutParams(dp(48), dp(60))
        )

        raiz.addView(barra)

        val tabs = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }

        val tabMascotas = TextView(this).apply {
            text = "MASCOTAS"
            gravity = Gravity.CENTER
            textSize = 15f
        }

        val tabPerfil = TextView(this).apply {
            text = "PERFIL"
            gravity = Gravity.CENTER
            textSize = 15f
        }

        tabs.addView(
            tabMascotas,
            LinearLayout.LayoutParams(0, dp(48), 1f)
        )

        tabs.addView(
            tabPerfil,
            LinearLayout.LayoutParams(0, dp(48), 1f)
        )

        raiz.addView(tabs)

        val pager = ViewPager2(this).apply {
            id = android.view.View.generateViewId()
            adapter = MascotasPagerAdapter(this@MainActivity)
        }

        raiz.addView(
            pager,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        fun actualizarTabs(posicion: Int) {
            val activo = Color.rgb(57, 73, 171)
            val inactivo = Color.GRAY

            tabMascotas.setTextColor(
                if (posicion == 0) activo else inactivo
            )

            tabPerfil.setTextColor(
                if (posicion == 1) activo else inactivo
            )
        }

        tabMascotas.setOnClickListener {
            pager.currentItem = 0
        }

        tabPerfil.setOnClickListener {
            pager.currentItem = 1
        }

        pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    actualizarTabs(position)
                }
            }
        )

        actualizarTabs(0)

        setContentView(raiz)
    }
}
