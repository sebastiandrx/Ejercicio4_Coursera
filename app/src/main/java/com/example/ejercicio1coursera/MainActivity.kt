
package com.example.ejercicio1coursera

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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
            contentDescription = "Ver mascotas favoritas"
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

        val opciones = TextView(this).apply {
            text = "⋮"
            textSize = 30f
            gravity = Gravity.CENTER
            contentDescription = "Opciones"

            setOnClickListener {
                val popup = PopupMenu(this@MainActivity, this)

                popup.menu.add(0, 1, 0, "Contacto")
                popup.menu.add(0, 2, 1, "Acerca De")

                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        1 -> {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    ContactoActivity::class.java
                                )
                            )
                            true
                        }
                        2 -> {
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
            opciones,
            LinearLayout.LayoutParams(dp(48), dp(60))
        )

        raiz.addView(barra)

        val tabs = TabLayout(this).apply {
            setBackgroundColor(Color.WHITE)
            setSelectedTabIndicatorColor(
                Color.rgb(77, 62, 66)
            )
            setTabTextColors(
                Color.GRAY,
                Color.rgb(77, 62, 66)
            )
        }

        raiz.addView(tabs)

        val pager = ViewPager2(this).apply {
            id = android.view.View.generateViewId()
            adapter = MascotasPagerAdapter(this@MainActivity)
        }

        raiz.addView(
            pager,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        setContentView(raiz)

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = when (position) {
                0 -> "MASCOTAS"
                1 -> "PERFIL"
                else -> ""
            }
        }.attach()
    }
}
