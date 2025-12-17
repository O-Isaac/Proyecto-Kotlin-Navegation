package com.codelab.proyectoab.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.codelab.proyectoab.MainActivity
import com.codelab.proyectoab.R

// Funciones adicionales para el bundle
fun Bundle.putTitle(title: String) = putString("EXTRA_TITLE", title)
fun Bundle.putCode(code: Int) = putInt("EXTRA_CODE", code)
fun Bundle.putMessage(message: String) = putString("EXTRA_MESSAGE", message)

// Clase que me permite manejar y crear canales
class NotificationChannelHelper(
    id: String,
    name: String,
    description: String
) {
    private lateinit var canal: NotificationChannel
    private lateinit var activity: MainActivity

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canal = NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT)
            canal.description = description
        }
    }

    fun register(mainActivity: MainActivity) {
        val manager = mainActivity.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(canal)
        activity = mainActivity;
    }


    fun notify(bundleBuilder: Bundle.() -> Unit) {
        if (!::activity.isInitialized) {
            throw IllegalStateException("No se ha usado register() antes de notify()")
        }

        val bundle = Bundle().apply(bundleBuilder)

        val title = bundle.getString("EXTRA_TITLE")
            ?: throw IllegalArgumentException("Falta el EXTRA_TITLE en el Bundle")

        val message = bundle.getString("EXTRA_MESSAGE")
            ?: throw IllegalArgumentException("Falta el EXTRA_MESSAGE en el Bundle")

        val code = bundle.getInt("EXTRA_CODE", -1)

        if (code == -1) {
            throw IllegalArgumentException("Falta el EXTRA_CODE en el Bundle")
        }

        // Quitamos estas claves del bundle para que no se pasen en extras
        bundle.remove("EXTRA_TITLE")
        bundle.remove("EXTRA_MESSAGE")
        bundle.remove("EXTRA_CODE")

        // Creamos el intent con los datos extra
        val intent = Intent(activity, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtras(bundle)
        }

        val pendingIntent = PendingIntent.getActivity(
            activity,
            code,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(activity, "CANAL_JUGADORES")
            .setSmallIcon(R.drawable.albacete_balompie)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(activity).notify(code, builder.build())
    }

}