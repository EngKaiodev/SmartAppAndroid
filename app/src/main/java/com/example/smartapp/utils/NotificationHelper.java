package com.example.smartapp.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.smartapp.R;

public class NotificationHelper {
    private static final String TAG = "NotificationHelper";
    public static final String DEFAULT_CHANNEL_ID = "smartapp_default_channel";
    public static final String DEFAULT_CHANNEL_NAME = "Notificações SmartApp";

    /**
     * Cria o NotificationChannel para Android O+.
     * Pode ser chamado com segurança múltiplas vezes.
     */
    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (nm == null) {
                Log.w(TAG, "NotificationManager is null; cannot create channel");
                return;
            }

            NotificationChannel existing = nm.getNotificationChannel(DEFAULT_CHANNEL_ID);
            if (existing == null) {
                NotificationChannel channel = new NotificationChannel(
                        DEFAULT_CHANNEL_ID,
                        DEFAULT_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                channel.setDescription("Canal padrão para notificações do SmartApp");
                nm.createNotificationChannel(channel);
                Log.d(TAG, "Notification channel created");
            } else {
                Log.d(TAG, "Notification channel already exists");
            }
        }
    }

    /**
     * Exibe uma notificação simples com título e corpo.
     * @param context contexto da app
     * @param title título da notificação
     * @param body  texto da notificação
     */
    public static void showNotification(Context context, String title, String body) {
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
                .setContentTitle(title == null ? "" : title)
                .setContentText(body == null ? "" : body)
                .setSmallIcon(R.drawable.ic_stat_ic_notification) // garanta que este drawable exista
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            int id = (int) System.currentTimeMillis(); // id simples para notificações únicas
            nm.notify(id, builder.build());
        } else {
            Log.w(TAG, "NotificationManager is null; cannot show notification");
        }
    }
}
