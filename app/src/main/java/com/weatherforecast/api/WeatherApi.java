package com.weatherforecast.api;

import com.weatherforecast.api.model.Welcome;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {
    @GET("/data/2.5/weather")
    Call<List<Welcome>> getWelcome();
}
