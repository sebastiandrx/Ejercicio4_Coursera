
package com.example.ejercicio1coursera

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class PublicacionMascota(
    val imagen: String,
    val color: Int,
    val rating: Int
)

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val ctx = requireContext()

        val raiz = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(Color.WHITE)
            setPadding(ctx.dp(12), ctx.dp(18), ctx.dp(12), 0)
        }

        val fotoPerfil = TextView(ctx).apply {
            text = "🐕"
            textSize = 65f
            gravity = Gravity.CENTER

            background = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.rgb(255, 128, 171))
            }
        }

        val fotoParams = LinearLayout.LayoutParams(
            ctx.dp(115),
            ctx.dp(115)
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }

        raiz.addView(fotoPerfil, fotoParams)

        val nombre = TextView(ctx).apply {
            text = "Catty"
            textSize = 26f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.rgb(77, 62, 66))
            gravity = Gravity.CENTER
        }

        raiz.addView(
            nombre,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ctx.dp(50)
            )
        )

        val subtitulo = TextView(ctx).apply {
            text = "Mis fotografías"
            textSize = 18f
            setTextColor(Color.DKGRAY)
            setPadding(0, ctx.dp(12), 0, ctx.dp(12))
        }

        raiz.addView(subtitulo)

        val publicaciones = listOf(
            PublicacionMascota("🐕", 0xFFFF80AB.toInt(), 5),
            PublicacionMascota("🐶", 0xFF64B5F6.toInt(), 3),
            PublicacionMascota("🐕", 0xFFFFCC80.toInt(), 8),
            PublicacionMascota("🐩", 0xFFA5D6A7.toInt(), 4),
            PublicacionMascota("🐕", 0xFFCE93D8.toInt(), 7),
            PublicacionMascota("🐶", 0xFF80CBC4.toInt(), 6)
        )

        val grid = RecyclerView(ctx).apply {
            layoutManager = GridLayoutManager(ctx, 2)
            adapter = PublicacionesAdapter(publicaciones)
            clipToPadding = false
        }

        raiz.addView(
            grid,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        return raiz
    }
}

class PublicacionesAdapter(
    private val publicaciones: List<PublicacionMascota>
) : RecyclerView.Adapter<PublicacionesAdapter.Holder>() {

    class Holder(
        val layout: LinearLayout,
        val imagen: TextView,
        val rating: TextView
    ) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {

        val ctx = parent.context

        val layout = LinearLayout(ctx).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(ctx.dp(5), ctx.dp(5), ctx.dp(5), ctx.dp(10))
        }

        val imagen = TextView(ctx).apply {
            textSize = 65f
            gravity = Gravity.CENTER
        }

        layout.addView(
            imagen,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ctx.dp(150)
            )
        )

        val rating = TextView(ctx).apply {
            textSize = 17f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(77, 62, 66))
        }

        layout.addView(rating)

        return Holder(layout, imagen, rating)
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        val item = publicaciones[position]

        holder.imagen.text = item.imagen
        holder.imagen.background = fondoRedondeado(
            item.color,
            16f
        )

        holder.rating.text = "🦴 ${item.rating}"
    }

    override fun getItemCount(): Int = publicaciones.size
}
