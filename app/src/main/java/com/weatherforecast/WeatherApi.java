package com.weatherforecast;

import com.weatherforecast.api.model.Welcome;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {

    @GET("data/2.5/weather?lat=48.442839&lon=34.795074&appid=e966672f6541a4685b1379348b8b6ef1&units=metric")
    Call<Welcome> getWelcomes();
}
