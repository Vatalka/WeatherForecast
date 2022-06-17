package com.weatherforecast.api.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sys {
    private String country;
    private long sunrise;
    private long sunset;

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long value) {
        this.sunrise = value;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long value) {
        this.sunset = value;
    }

    // рассвет в человекопонятное время
    public String getSunriseTime() {
        Date date = new Date(sunrise * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    // закат в человекопонятное время
    public String getSunsetTime() {
        Date date = new Date(sunset * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }
}