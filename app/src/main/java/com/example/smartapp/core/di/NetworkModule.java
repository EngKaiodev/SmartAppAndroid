package com.example.smartapp.core.di;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.smartapp.BuildConfig;
import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.network.AuthInterceptor;
import com.example.smartapp.core.network.service.AuthService;
import com.example.smartapp.core.network.service.ClienteService;
import com.example.smartapp.core.network.service.EnderecoService;
import com.example.smartapp.core.network.service.HourService;
import com.example.smartapp.core.network.service.ProfissionalService;
import com.example.smartapp.core.network.service.ReviewService;
import com.example.smartapp.core.network.service.ScheduleService;
import com.example.smartapp.core.network.service.ServicoService;
import com.example.smartapp.domain.repository.AuthRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RequiresApi(api = Build.VERSION_CODES.O)
@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String BASE_URL = "http://" + BuildConfig.LOCAL_HOST_IP + ":8080/";

    @Provides
    @Singleton
    public static Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>)
                        (json, type, context) -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                        (json, type, context) -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .create();
    }

    @Provides
    @Singleton
    public static AuthInterceptor providesAuthInterceptor(Preferences preferences, AuthRepository authRepository) {
        return new AuthInterceptor(preferences, authRepository);
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(AuthInterceptor authInterceptor) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }


    @Provides
    @Singleton
    @Named("defaultRetrofit")
    public static Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();
    }


    @Provides
    @Singleton
    @Named("refreshRetrofit")
    public static Retrofit provideRefreshRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    public static EnderecoService provideEnderecoService(@Named("defaultRetrofit") Retrofit retrofit) {
        return retrofit.create(EnderecoService.class);
    }

    @Provides
    @Singleton
    public static AuthService provideAuthService(@Named("refreshRetrofit") Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }

    @Provides
    @Singleton
    public static ProfissionalService provideProfissionalService(@Named("defaultRetrofit") Retrofit retrofit) {
        return retrofit.create(ProfissionalService.class);
    }

    @Provides
    @Singleton
    public static HourService provideHourService(@Named("defaultRetrofit") Retrofit retrofit) {
        return retrofit.create(HourService.class);
    }

    @Provides
    @Singleton
    public static ServicoService provideServicoService(@Named("defaultRetrofit") Retrofit retrofit) {
        return retrofit.create(ServicoService.class);
    }

    @Provides
    @Singleton
    public static ScheduleService provideScheduleService(@Named("defaultRetrofit") Retrofit retrofit){
        return retrofit.create(ScheduleService.class);
    }

    @Provides
    @Singleton
    public static ReviewService provideReviewService(@Named("defaultRetrofit") Retrofit retrofit){
        return retrofit.create(ReviewService.class);
    }

    @Provides
    @Singleton
    public static ClienteService provideClienteService(@Named("defaultRetrofit") Retrofit retrofit){
        return retrofit.create(ClienteService.class);
    }
}
