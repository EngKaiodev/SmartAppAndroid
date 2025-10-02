package com.example.smartapp.fragments.registro;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartapp.databinding.GerenciarNotificacoesBinding;
import com.example.smartapp.utils.NotificationHelper;
import com.example.smartapp.viewmodels.GerenciarNotificacoesViewModel;
import com.google.firebase.messaging.FirebaseMessaging;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GerenciarNotificacoesFragment extends Fragment {

    private static final String TAG = "GerenciarNotifFrag";
    private static final String PREFS_NAME = "smartapp_prefs";
    private static final String KEY_ALL = "pref_all";
    private static final String KEY_SOUND = "pref_sound";
    private static final String KEY_VIBRATE = "pref_vibrate";
    private static final String KEY_CONFIRMED = "pref_confirmed";
    private static final String KEY_REMINDER_1D = "pref_reminder_1d";
    private static final String KEY_REMINDER_1H = "pref_reminder_1h";
    private static final String KEY_CANCELLED = "pref_cancelled";
    private static final String KEY_MESSAGES = "pref_messages";
    private static final String KEY_PROMOTIONS = "pref_promotions";

    private GerenciarNotificacoesBinding binding;
    private GerenciarNotificacoesViewModel viewModel;
    private NavController navController;
    private SharedPreferences prefs;

    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = GerenciarNotificacoesBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(GerenciarNotificacoesViewModel.class);

        prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // registrar request permission launcher (Android 13+)
        requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        Log.d(TAG, "Notification permission granted");
                    } else {
                        Log.d(TAG, "Notification permission denied");
                    }
                });

        // Cria canal de notificação (Android 8+)
        NotificationHelper.createNotificationChannel(requireContext());

        // Pega token (apenas para debug/registro)
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d(TAG, "FCM Token: " + token);
                    // TODO: envie para servidor se necessário
                });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        super.onViewCreated(view, savedInstanceState);

        setupListeners();
        loadPreferences();
        checkNotificationPermission();
    }

    private void setupListeners() {
        binding.btnvoltarNotificacao.setOnClickListener(v -> navController.popBackStack());

        // toggles: quando mudarem, salvo pref e atualizo assinatura de tópico
        binding.toggleAll.setOnCheckedChangeListener(this::onToggleAllChanged);
        binding.toggleSound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(KEY_SOUND, isChecked).apply();
        });
        binding.toggleVibrate.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_VIBRATE, isChecked).apply();
        });

        // agendamentos
        binding.toggleConfirmed.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_CONFIRMED, isChecked).apply();
            subscribeTopic("scheduling_confirmed", isChecked);
        });
        binding.toggleReminder1Day.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_REMINDER_1D, isChecked).apply();
            subscribeTopic("scheduling_reminder_1d", isChecked);
        });
        binding.toggleReminder1Hour.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_REMINDER_1H, isChecked).apply();
            subscribeTopic("scheduling_reminder_1h", isChecked);
        });
        binding.toggleCancelled.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_CANCELLED, isChecked).apply();
            subscribeTopic("scheduling_cancelled", isChecked);
        });

        // outras
        binding.toggleMessages.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_MESSAGES, isChecked).apply();
            subscribeTopic("messages", isChecked);
        });
        binding.togglePromotions.setOnCheckedChangeListener((b, isChecked) -> {
            prefs.edit().putBoolean(KEY_PROMOTIONS, isChecked).apply();
            subscribeTopic("promotions", isChecked);
        });
    }

    private void onToggleAllChanged(CompoundButton buttonView, boolean isChecked) {
        prefs.edit().putBoolean(KEY_ALL, isChecked).apply();

        // Se desativar All -> desligar todos os outros toggles
        if (!isChecked) {
            // evitar disparar listeners muitas vezes: setChecked sem disparar? aqui usamos setChecked normalmente.
            binding.toggleSound.setChecked(false);
            binding.toggleVibrate.setChecked(false);
            binding.toggleConfirmed.setChecked(false);
            binding.toggleReminder1Day.setChecked(false);
            binding.toggleReminder1Hour.setChecked(false);
            binding.toggleCancelled.setChecked(false);
            binding.toggleMessages.setChecked(false);
            binding.togglePromotions.setChecked(false);
        } else {
            // ativar defaults (você pode ajustar)
            binding.toggleSound.setChecked(true);
            binding.toggleVibrate.setChecked(true);
            binding.toggleConfirmed.setChecked(true);
            binding.toggleReminder1Day.setChecked(true);
            binding.toggleReminder1Hour.setChecked(true);
            binding.toggleCancelled.setChecked(true);
            binding.toggleMessages.setChecked(true);
            binding.togglePromotions.setChecked(false); // mantendo default
        }

        // Como os listeners de cada toggle já chamam subscribeTopic/unsubscribe, não precisamos chamar aqui.
    }

    private void loadPreferences() {
        binding.toggleAll.setChecked(prefs.getBoolean(KEY_ALL, true));
        binding.toggleSound.setChecked(prefs.getBoolean(KEY_SOUND, true));
        binding.toggleVibrate.setChecked(prefs.getBoolean(KEY_VIBRATE, false));
        binding.toggleConfirmed.setChecked(prefs.getBoolean(KEY_CONFIRMED, true));
        binding.toggleReminder1Day.setChecked(prefs.getBoolean(KEY_REMINDER_1D, true));
        binding.toggleReminder1Hour.setChecked(prefs.getBoolean(KEY_REMINDER_1H, true));
        binding.toggleCancelled.setChecked(prefs.getBoolean(KEY_CANCELLED, true));
        binding.toggleMessages.setChecked(prefs.getBoolean(KEY_MESSAGES, true));
        binding.togglePromotions.setChecked(prefs.getBoolean(KEY_PROMOTIONS, false));
    }

    private void subscribeTopic(String topic, boolean subscribe) {
        if (!binding.getRoot().isShown()) {
            // segurança; mas mesmo se não mostrado, podemos inscrever
        }
        if (subscribe) {
            FirebaseMessaging.getInstance().subscribeToTopic(topic)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) Log.d(TAG, "Subscribed to topic: " + topic);
                        else Log.w(TAG, "Failed to subscribe to topic: " + topic, task.getException());
                    });
        } else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) Log.d(TAG, "Unsubscribed from topic: " + topic);
                        else Log.w(TAG, "Failed to unsubscribe from topic: " + topic, task.getException());
                    });
        }
    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
                    != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // pedir permissão
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
