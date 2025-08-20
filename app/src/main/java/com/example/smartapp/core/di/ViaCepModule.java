package com.example.smartapp.core.di;


import com.example.smartapp.core.network.service.ViaCepService;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ViaCepModule {

    private static final String VIA_CEP_URL = "";

    @Provides
    @Singleton
    @Named("ViaCepRetrofit")
    public static Retrofit profideRetrofitViaCep(Gson gson, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(VIA_CEP_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public static ViaCepService provideViaCepService(@Named("ViaCepRetrofit") Retrofit retrofit) {
        return retrofit.create(ViaCepService.class);
    }
}
