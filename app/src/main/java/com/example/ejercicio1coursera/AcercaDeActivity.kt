
package com.example.ejercicio1coursera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AcercaDeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(18.dp)
                ) {

                    Text(
                        text = "Acerca de",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4D3E42)
                    )

                    HorizontalDivider()

                    Text(
                        text = "Juan Sebastián Dueñas Robayo",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Desarrollador de software",
                        fontSize = 17.sp
                    )

                    Text(
                        text = """
                        Estudiante de Ingeniería de Sistemas
                        de UNIMINUTO, con conocimientos en
                        desarrollo de aplicaciones móviles,
                        programación y bases de datos.

                        Interesado en crear soluciones
                        tecnológicas que contribuyan a
                        mejorar procesos y experiencias
                        de los usuarios.
                        """.trimIndent()
                    )

                    HorizontalDivider()

                    Text(
                        text = "Proyecto Petagram",
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = """
                        Aplicación académica desarrollada
                        con Kotlin y Android Studio.

                        Incluye:
                        • Material Design
                        • RecyclerView
                        • Adapter y ViewHolder
                        • Fragments
                        • ViewPager2
                        • Galería de mascotas
                        • Formulario de contacto
                        • Integración JavaMail
                        """.trimIndent()
                    )

                    Button(
                        onClick = { finish() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("REGRESAR")
                    }
                }
            }
        }
    }
}
