
package com.example.ejercicio1coursera

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ContactoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        val nombre = findViewById<TextInputEditText>(R.id.etNombre)
        val correo = findViewById<TextInputEditText>(R.id.etCorreo)
        val mensaje = findViewById<TextInputEditText>(R.id.etMensaje)

        val layoutNombre =
            findViewById<TextInputLayout>(R.id.layoutNombre)
        val layoutCorreo =
            findViewById<TextInputLayout>(R.id.layoutCorreo)
        val layoutMensaje =
            findViewById<TextInputLayout>(R.id.layoutMensaje)

        findViewById<MaterialButton>(R.id.btnEnviar)
            .setOnClickListener {

                val n = nombre.text.toString().trim()
                val c = correo.text.toString().trim()
                val m = mensaje.text.toString().trim()

                layoutNombre.error =
                    if (n.isEmpty()) "Ingresa tu nombre" else null

                layoutCorreo.error =
                    if (!Patterns.EMAIL_ADDRESS.matcher(c).matches())
                        "Correo inválido" else null

                layoutMensaje.error =
                    if (m.isEmpty()) "Escribe tu mensaje" else null

                if (n.isNotEmpty() &&
                    Patterns.EMAIL_ADDRESS.matcher(c).matches() &&
                    m.isNotEmpty()
                ) {
                    configurarEnvio(n, c, m)
                }
            }
    }

    private fun configurarEnvio(
        nombre: String,
        correo: String,
        mensaje: String
    ) {
        val formulario = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 12, 40, 0)
        }

        fun campo(hint: String): EditText {
            return EditText(this).apply {
                this.hint = hint
                formulario.addView(this)
            }
        }

        val cuenta = campo("Cuenta Gmail de envío")
        val clave = campo("Contraseña de aplicación").apply {
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        val destino = campo("Correo de destino")

        val dialogo = AlertDialog.Builder(this)
            .setTitle("Enviar comentario")
            .setMessage("Configuración SMTP para la prueba")
            .setView(formulario)
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Enviar", null)
            .create()

        dialogo.setOnShowListener {
            val boton = dialogo.getButton(
                AlertDialog.BUTTON_POSITIVE
            )

            boton.setOnClickListener {
                val usuario = cuenta.text.toString().trim()
                val password = clave.text.toString()
                val receptor = destino.text.toString().trim()

                if (!Patterns.EMAIL_ADDRESS.matcher(usuario).matches() ||
                    password.isBlank() ||
                    !Patterns.EMAIL_ADDRESS.matcher(receptor).matches()
                ) {
                    Toast.makeText(
                        this,
                        "Revisa la configuración SMTP",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                boton.isEnabled = false

                Thread {
                    val resultado = CorreoSender.enviar(
                        nombre, correo, mensaje,
                        usuario, password, receptor
                    )

                    runOnUiThread {
                        if (!isFinishing && !isDestroyed) {
                            Toast.makeText(
                                this,
                                resultado,
                                Toast.LENGTH_LONG
                            ).show()
                            dialogo.dismiss()
                        }
                    }
                }.start()
            }
        }

        dialogo.show()
    }
}
