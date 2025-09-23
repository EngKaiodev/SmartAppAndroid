package com.example.smartapp.core.network;

import androidx.annotation.NonNull;

import com.example.smartapp.core.Preferences;
import com.example.smartapp.core.domain.response.RefreshResponse;
import com.example.smartapp.domain.repository.AuthRepository;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final Preferences preferences;
    private final AuthRepository authRepository;

    @Inject
    public AuthInterceptor(Preferences preferences, AuthRepository authRepository) {
        this.preferences = preferences;
        this.authRepository = authRepository;
    }


    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        String token = preferences.getToken();
        Request request = chain.request();

        Request original = chain.request();
        Request.Builder builder = original.newBuilder();

        if (token != null && !token.isBlank()){
            request = builder.addHeader("Authorization", "Bearer " + token).build();
        }

        Response response = chain.proceed(request);

        if (response.code() == 401 || response.code() ==403){
            response.close();

            retrofit2.Response<RefreshResponse> refreshResponse = authRepository.refresh(preferences.getRefreshToken());

            if (refreshResponse.isSuccessful() && refreshResponse.body() != null){
                preferences.updateToken(refreshResponse.body().getToken());

                Request newRequest = request.newBuilder()
                        .removeHeader("Authorization")
                        .addHeader("Authorization", "Bearer " + preferences.getToken())
                        .build();

                return chain.proceed(newRequest);
            }
        }
        return response;
    }
}
