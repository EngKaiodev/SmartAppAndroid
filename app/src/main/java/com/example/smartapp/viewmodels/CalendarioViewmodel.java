package com.example.smartapp.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * ViewModel que lê schedule salvo (SharedPreferences) e gera slots disponíveis para uma data.
 */
public class CalendarioViewmodel extends AndroidViewModel {

    private static final String PREFS_NAME = "smartapp_schedules_prefs";
    private static final String KEY_DAYS_CSV = "daysCsv";
    private static final String KEY_START_MORNING = "startMorning";
    private static final String KEY_END_MORNING = "endMorning";
    private static final String KEY_START_AFTERNOON = "startAfternoon";
    private static final String KEY_END_AFTERNOON = "endAfternoon";
    private static final String KEY_SLOT_INTERVAL = "slotInterval";

    private final SharedPreferences prefs;
    private final Executor executor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<List<String>> availableSlots = new MutableLiveData<>();
    private final MutableLiveData<ScheduleData> cachedSchedule = new MutableLiveData<>();

    public CalendarioViewmodel(@NonNull Application application) {
        super(application);
        prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loadScheduleFromPrefs();
    }

    public LiveData<List<String>> getAvailableSlots() {
        return availableSlots;
    }

    public LiveData<ScheduleData> getCachedSchedule() {
        return cachedSchedule;
    }

    public void clearAvailableSlots() {
        availableSlots.postValue(new ArrayList<>());
    }

    /**
     * Retorna lista de dias salvos no formato 1=Seg ... 7=Dom (prática usada no GerenciarHorariosViewModel).
     */
    public List<Integer> getSavedDays() {
        ScheduleData sd = cachedSchedule.getValue();
        if (sd == null) sd = readScheduleSync();
        return sd.days;
    }

    /**
     * Verifica se a data (millis) cai em um dia permitido pela configuração salva.
     */
    public boolean isDateAllowed(long dateMillis) {
        ScheduleData sd = cachedSchedule.getValue();
        if (sd == null) sd = readScheduleSync();

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dateMillis);
        int calendarDow = c.get(Calendar.DAY_OF_WEEK); // 1 (SUN) .. 7 (SAT)

        if (sd.days == null || sd.days.isEmpty()) return false;

        for (Integer savedDay : sd.days) {
            int mapped = (savedDay % 7) + 1; // saved:1(Mon)->mapped:2(MON)
            if (mapped == calendarDow) return true;
        }
        return false;
    }

    /**
     * Gera slots para a data e publica em availableSlots (assíncrono).
     */
    public void generateSlotsForDate(long dateMillis) {
        executor.execute(() -> {
            ScheduleData sd = cachedSchedule.getValue();
            if (sd == null) sd = readScheduleSync();

            List<String> result = new ArrayList<>();
            if (sd == null || sd.days == null || sd.days.isEmpty()) {
                availableSlots.postValue(result);
                return;
            }

            // Confere novamente o dia
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(dateMillis);
            int calendarDow = c.get(Calendar.DAY_OF_WEEK);
            boolean allowed = false;
            for (Integer savedDay : sd.days) {
                int mapped = (savedDay % 7) + 1;
                if (mapped == calendarDow) { allowed = true; break; }
            }
            if (!allowed) {
                availableSlots.postValue(result);
                return;
            }

            // Gera slots manhã + tarde
            result.addAll(generateSlots(sd.startMorning, sd.endMorning, sd.slotInterval));
            result.addAll(generateSlots(sd.startAfternoon, sd.endAfternoon, sd.slotInterval));
            availableSlots.postValue(result);
        });
    }

    private List<String> generateSlots(int startInclusiveMin, int endExclusiveMin, int intervalMin) {
        List<String> out = new ArrayList<>();
        if (intervalMin <= 0) intervalMin = 60;
        for (int t = startInclusiveMin; t + intervalMin <= endExclusiveMin; t += intervalMin) {
            out.add(minutesToHHmm(t));
        }
        return out;
    }

    private String minutesToHHmm(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    private void loadScheduleFromPrefs() {
        executor.execute(() -> {
            ScheduleData sd = readScheduleSync();
            cachedSchedule.postValue(sd);
        });
    }

    private ScheduleData readScheduleSync() {
        String daysCsv = prefs.getString(KEY_DAYS_CSV, "");
        int startMorning = prefs.getInt(KEY_START_MORNING, 8 * 60);
        int endMorning = prefs.getInt(KEY_END_MORNING, 12 * 60);
        int startAfternoon = prefs.getInt(KEY_START_AFTERNOON, 14 * 60);
        int endAfternoon = prefs.getInt(KEY_END_AFTERNOON, 18 * 60);
        int slotInterval = prefs.getInt(KEY_SLOT_INTERVAL, 60);

        List<Integer> days = csvToList(daysCsv);
        return new ScheduleData(days, startMorning, endMorning, startAfternoon, endAfternoon, slotInterval);
    }

    private List<Integer> csvToList(String csv) {
        List<Integer> out = new ArrayList<>();
        if (csv == null || csv.trim().isEmpty()) return out;
        String[] parts = csv.split(",");
        for (String p : parts) {
            try { out.add(Integer.parseInt(p.trim())); }
            catch (NumberFormatException ignored) {}
        }
        return out;
    }

    /** DTO */
    public static class ScheduleData {
        public final List<Integer> days;
        public final int startMorning;
        public final int endMorning;
        public final int startAfternoon;
        public final int endAfternoon;
        public final int slotInterval;

        public ScheduleData(List<Integer> days, int startMorning, int endMorning, int startAfternoon, int endAfternoon, int slotInterval) {
            this.days = days;
            this.startMorning = startMorning;
            this.endMorning = endMorning;
            this.startAfternoon = startAfternoon;
            this.endAfternoon = endAfternoon;
            this.slotInterval = slotInterval;
        }
    }
}
