package id.saipulmuiz.forwaapp.data.service

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import id.saipulmuiz.forwaapp.ui.MainActivity
import id.saipulmuiz.forwaapp.util.ALARM_ID_REPEATING
import id.saipulmuiz.forwaapp.util.ALARM_MESSAGE
import id.saipulmuiz.forwaapp.util.ALARM_TITTLE

// Home fragment implements broadcast receiver
class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val title = intent?.getStringExtra(ALARM_TITTLE)
        val message = intent?.getStringExtra(ALARM_MESSAGE)

        val notificationIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)

        context?.let {
            AlarmHelper.showNotification(
                it,
                title ?: "Tittle",
                message ?: "Message",
                ALARM_ID_REPEATING,
                pendingIntent
            )
        }
    }
}
