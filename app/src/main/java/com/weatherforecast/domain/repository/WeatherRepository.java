package com.weatherforecast.domain.repository;

import com.weatherforecast.data.api.WeatherApi;
import com.weatherforecast.domain.models.Forecast;
import com.weatherforecast.domain.retrofit.RetrofitClient;

import io.reactivex.rxjava3.core.Single;

public class WeatherRepository {

    private final WeatherApi WEATHER_API = RetrofitClient.getWeatherApi();

    double lat = 34.794703;
    double lon = 48.450119;
    String appid = "e966672f6541a4685b1379348b8b6ef1";
    String lang = "ru";

    public Single<Forecast> getWeather() {
        return WEATHER_API.getForecast(lon, lat, appid, lang);
    }
}
