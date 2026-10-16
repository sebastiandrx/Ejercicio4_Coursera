
package com.example.ejercicio1coursera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class FotoMascota(
    val imagen: Int,
    val rating: Int
)

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val vista = inflater.inflate(
            R.layout.fragment_perfil,
            container,
            false
        )

        val recycler = vista.findViewById<RecyclerView>(
            R.id.recyclerFotos
        )

        val publicaciones = listOf(
            FotoMascota(R.drawable.perro1, 5),
            FotoMascota(R.drawable.perro2, 3),
            FotoMascota(R.drawable.perro3, 8),
            FotoMascota(R.drawable.perro4, 4),
            FotoMascota(R.drawable.perro5, 7),
            FotoMascota(R.drawable.perro6, 6)
        )

        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            2
        )

        recycler.adapter = FotosAdapter(publicaciones)

        return vista
    }
}

class FotosAdapter(
    private val fotos: List<FotoMascota>
) : RecyclerView.Adapter<FotosAdapter.FotoViewHolder>() {

    class FotoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imagen: ImageView =
            itemView.findViewById(R.id.imagenFoto)

        val rating: TextView =
            itemView.findViewById(R.id.ratingFoto)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FotoViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_foto, parent, false)

        return FotoViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: FotoViewHolder,
        position: Int
    ) {
        val foto = fotos[position]

        holder.imagen.setImageResource(foto.imagen)
        holder.rating.text = "🦴 ${foto.rating}"
    }

    override fun getItemCount(): Int = fotos.size
}
