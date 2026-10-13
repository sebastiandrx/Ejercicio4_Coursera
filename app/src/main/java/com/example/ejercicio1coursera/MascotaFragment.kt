
package com.example.ejercicio1coursera

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MascotasFragment : Fragment() {

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val contexto = requireContext()

        return RecyclerView(contexto).apply {
            layoutManager = LinearLayoutManager(contexto)

            adapter = MascotaAdapter(
                MascotasData.mascotas
            )

            setPadding(
                contexto.dp(14),
                contexto.dp(12),
                contexto.dp(14),
                contexto.dp(20)
            )

            clipToPadding = false
        }
    }
}
