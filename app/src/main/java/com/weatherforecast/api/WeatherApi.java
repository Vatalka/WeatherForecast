package com.weatherforecast.api;

import com.weatherforecast.api.model.Welcome;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface WeatherApi {

    @GET("data/2.5/weather?lat=48.450119&lon=34.794703&appid=e966672f6541a4685b1379348b8b6ef1&units=metric")
    Single<Welcome> getWelcomes();
}
