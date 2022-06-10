package com.weatherforecast;

import android.app.Application;

import com.weatherforecast.api.WeatherService;

public class App extends Application {

    private WeatherService weatherService;

    @Override
    public void onCreate() {
        super.onCreate();

        // инициализирую сетевой сервис, которым буду делать запрос к серверу
        weatherService = new WeatherService();
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }
}
