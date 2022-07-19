package com.weatherforecast.domain.retrofit;

import com.weatherforecast.data.api.WeatherApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://api.openweathermap.org/";
    private static final HttpLoggingInterceptor INTERCEPTOR = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .addInterceptor(INTERCEPTOR).build();
    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(CLIENT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public static WeatherApi getWeatherApi() {
        return RETROFIT.create(WeatherApi.class);
    }
}
