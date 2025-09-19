package com.example.smartapp.core.di;

import com.example.smartapp.BuildConfig;
import com.example.smartapp.core.network.service.AuthService;
import com.example.smartapp.core.network.service.EnderecoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    private static final String BASE_URL = "http://" + BuildConfig.LOCAL_HOST_IP + ":8080/";


    @Provides
    @Singleton
    public static Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
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
    public static EnderecoService provideEnderecoService(Retrofit retrofit) {
        return retrofit.create(EnderecoService.class);
    }

    @Provides
    @Singleton
    public static AuthService provideAuthService(@Named("defaultRetrofit") Retrofit retrofit) {
        return retrofit.create(AuthService.class);
    }
}
