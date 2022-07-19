package com.weatherforecast.data.api;

import com.weatherforecast.domain.models.Forecast;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("data/2.5/weather?units=metric")
    Single<Forecast> getForecast(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("appid") String appid,
            @Query("lang") String lang);
}
