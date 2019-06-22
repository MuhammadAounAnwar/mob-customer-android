package com.locatemybus.myorderbox.Network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.locatemybus.myorderbox.BuildConfig;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static ApiService service;
    private static String apiKey = "cedb9ca46fba59ddc9b1aefd3fccd018";
    private static String token = "";
//    private static String token = "";


    public static void setToken(String token) {
        RestClient.token = token;
    }

    public static ApiService getInstance(Context context, boolean isUploading, boolean isSalesForce) {

        if (service != null) {
            service = null;
        }
        if (service == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                    .create();
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

                builder.readTimeout(50, TimeUnit.SECONDS);
                builder.connectTimeout(50, TimeUnit.SECONDS);
                builder.writeTimeout(50, TimeUnit.SECONDS);

            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("X-API-Key", apiKey)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(request);
                }
            });
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addNetworkInterceptor(interceptor);
            }
            int cacheSize = 2 * 1024 * 1024; // 2 MiB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);
            builder.cache(cache);
            String baseURL = AppWebServices.BASE_URL;

            Retrofit retrofit = new Retrofit
                    .Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(baseURL).build();
            service = retrofit.create(ApiService.class);

            return service;
        } else {
            return service;
        }
    }
}
