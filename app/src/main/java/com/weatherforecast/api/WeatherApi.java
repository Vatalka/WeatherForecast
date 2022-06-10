package com.weatherforecast.api;

import com.weatherforecast.api.model.Welcome;

import java.util.List;

import retrofit2.http.GET;
import rx.Single;

public interface WeatherApi {
    @GET("/data/2.5/weather")
    Single<List<Welcome>> getWeather();
}
