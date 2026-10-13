
package com.example.ejercicio1coursera

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ContactoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF75E2E9),
                    onPrimary = Color(0xFF4D3E42),
                    secondary = Color(0xFFEF5344)
                )
            ) {
                FormularioContacto { nombre, correo, mensaje,
                                     cuenta, clave, destino, resultado ->

                    Thread {
                        val respuesta = CorreoSender.enviar(
                            nombre = nombre,
                            correo = correo,
                            mensaje = mensaje,
                            cuenta = cuenta,
                            clave = clave,
                            destino = destino
                        )

                        runOnUiThread {
                            resultado(respuesta)
                        }
                    }.start()
                }
            }
        }
    }
}

@Composable
fun FormularioContacto(
    onEnviar: (
        String, String, String, String,
        String, String, (String) -> Unit
    ) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    var mostrarConfiguracion by remember {
        mutableStateOf(false)
    }

    var resultado by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Contáctanos",
            fontSize = 30.sp,
            color = Color(0xFF4D3E42)
        )

        Text("Envíanos tus comentarios sobre Petagram.")

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = mensaje,
            onValueChange = { mensaje = it },
            label = { Text("Tu mensaje") },
            minLines = 5,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (
                    nombre.isNotBlank() &&
                    Patterns.EMAIL_ADDRESS.matcher(correo).matches() &&
                    mensaje.isNotBlank()
                ) {
                    resultado = ""
                    mostrarConfiguracion = true
                } else {
                    resultado = "Completa los campos y revisa el correo."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("ENVIAR COMENTARIO")
        }

        if (resultado.isNotEmpty()) {
            Text(resultado)
        }
    }

    if (mostrarConfiguracion) {

        var cuenta by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("") }
        var destino by remember { mutableStateOf("") }

        var enviando by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = {
                if (!enviando) mostrarConfiguracion = false
            },

            title = {
                Text("Configuración de envío")
            },

            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        "Para esta demostración, ingresa una cuenta " +
                                "SMTP de Gmail y su contraseña de aplicación."
                    )

                    OutlinedTextField(
                        value = cuenta,
                        onValueChange = { cuenta = it },
                        label = { Text("Cuenta Gmail de envío") },
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña de aplicación") },
                        visualTransformation =
                            PasswordVisualTransformation(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = destino,
                        onValueChange = { destino = it },
                        label = { Text("Correo de destino") },
                        singleLine = true
                    )

                    if (enviando) {
                        CircularProgressIndicator()
                    }
                }
            },

            confirmButton = {
                TextButton(
                    enabled = !enviando &&
                            cuenta.isNotBlank() &&
                            clave.isNotBlank() &&
                            Patterns.EMAIL_ADDRESS
                                .matcher(destino).matches(),
                    onClick = {
                        enviando = true

                        onEnviar(
                            nombre,
                            correo,
                            mensaje,
                            cuenta,
                            clave,
                            destino
                        ) { respuesta ->
                            enviando = false
                            resultado = respuesta
                            mostrarConfiguracion = false
                        }
                    }
                ) {
                    Text("ENVIAR")
                }
            },

            dismissButton = {
                TextButton(
                    enabled = !enviando,
                    onClick = {
                        mostrarConfiguracion = false
                    }
                ) {
                    Text("CANCELAR")
                }
            }
        )
    }
}
