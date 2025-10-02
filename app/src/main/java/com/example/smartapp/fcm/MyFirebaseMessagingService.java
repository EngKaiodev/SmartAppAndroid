package com.example.smartapp.fcm;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.smartapp.utils.NotificationHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "FCM token refreshed: " + token);
        // TODO: envie esse token para o seu servidor para armazenar e poder mandar push
        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Verifica payload de dados
        if (remoteMessage.getData() != null && !remoteMessage.getData().isEmpty()) {
            Map<String, String> data = remoteMessage.getData();
            Log.d(TAG, "Message data payload: " + data.toString());

            // Exemplo: se você enviar título e body em data, use-os. Caso contrário, use RemoteMessage.notification
            String title = data.containsKey("title") ? data.get("title") : "SmartApp";
            String body = data.containsKey("body") ? data.get("body") : data.toString();

            // Exibe notificação customizada (útil em foreground)
            NotificationHelper.showNotification(getApplicationContext(), title, body);
        }

        // Se há payload de notificação (notification payload)
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();

            // Se o app estiver em foreground, o sistema não mostra automaticamente:
            // chamamos nosso helper para exibir
            NotificationHelper.showNotification(getApplicationContext(),
                    title != null ? title : "SmartApp",
                    body != null ? body : "");
        }
    }

    private void sendRegistrationToServer(String token) {
        // TODO: implementar envio do token ao servidor via API (retrofit/okhttp/etc.)
        // Exemplo: chamar endpoint POST /device/register com { token, userId }
        Log.d(TAG, "sendRegistrationToServer: (implementar envio) token = " + token);
    }
}
