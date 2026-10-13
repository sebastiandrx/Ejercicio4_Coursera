
package com.example.ejercicio1coursera

import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object CorreoSender {

    fun enviar(
        nombre: String,
        correo: String,
        mensaje: String,
        cuenta: String,
        clave: String,
        destino: String
    ): String {

        return try {

            val propiedades = Properties().apply {
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.port", "587")
                put("mail.smtp.ssl.protocols", "TLSv1.2")
                put("mail.smtp.connectiontimeout", "15000")
                put("mail.smtp.timeout", "15000")
                put("mail.smtp.writetimeout", "15000")
            }

            val sesion = Session.getInstance(
                propiedades,
                object : Authenticator() {
                    override fun getPasswordAuthentication():
                            PasswordAuthentication {
                        return PasswordAuthentication(
                            cuenta,
                            clave
                        )
                    }
                }
            )

            val email = MimeMessage(sesion).apply {
                setFrom(InternetAddress(cuenta.trim()))
                setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destino.trim())
                )

                replyTo = arrayOf(
                    InternetAddress(correo.trim())
                )

                subject = "Nuevo comentario desde Petagram"

                setText(
                    """
                    Nuevo mensaje de contacto

                    Nombre: $nombre
                    Correo: $correo

                    Mensaje:
                    $mensaje
                    """.trimIndent(),
                    "UTF-8"
                )
            }

            Transport.send(email)

            "Comentario enviado correctamente."

        } catch (e: Exception) {
            "No se pudo enviar el correo: " +
                    (e.localizedMessage ?: "Error de conexión SMTP")
        }
    }
}
