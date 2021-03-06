package com.kabouzeid.gramophone.lastfm.rest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kabouzeid.gramophone.lastfm.rest.service.LastFMService;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class LastFMRestClient {
    public static final String BASE_URL = "https://ws.audioscrobbler.com/2.0/";

    private LastFMService apiService;

    public LastFMRestClient(@NonNull Context context) {
        this(createDefaultOkHttpClientBuilder(context).build());
    }

    public LastFMRestClient(@NonNull Call.Factory client) {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .callFactory(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = restAdapter.create(LastFMService.class);
    }

    public LastFMService getApiService() {
        return apiService;
    }

    @Nullable
    public static Cache createDefaultCache(Context context) {
        File cacheDir = new File(context.getCacheDir().getAbsolutePath(), "/okhttp-lastfm/");
        if (cacheDir.mkdirs() || cacheDir.isDirectory()) {
            return new Cache(cacheDir, 1024 * 1024 * 10);
        }
        return null;
    }

    public static Interceptor createCacheControlInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request modifiedRequest = chain.request().newBuilder()
                        .addHeader("Cache-Control", String.format("max-age=%d, max-stale=%d", 31536000, 31536000))
                        .build();
                return chain.proceed(modifiedRequest);
            }
        };
    }

    public static OkHttpClient.Builder createDefaultOkHttpClientBuilder(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("LastFMRestClient", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .cache(createDefaultCache(context))
                .addInterceptor(createCacheControlInterceptor());
    }
}
