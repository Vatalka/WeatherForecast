package com.weatherforecast.api.model;

public class Sys {
    private String country;
    private long sunrise;
    private long sunset;

    public String getCountry() { return country; }
    public void setCountry(String value) { this.country = value; }

    public long getSunrise() { return sunrise; }
    public void setSunrise(long value) { this.sunrise = value; }

    public long getSunset() { return sunset; }
    public void setSunset(long value) { this.sunset = value; }
}
