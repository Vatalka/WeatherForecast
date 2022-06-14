package com.weatherforecast.api.model;

import android.annotation.SuppressLint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Sys {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private long sunrise;
    @SerializedName("sunset")
    @Expose
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
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return sdf.format(date);
    }

    // закат в человекопонятное время
    public String getSunsetTime() {
        Date date = new Date(sunset * 1000L);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return sdf.format(date);
    }
}
