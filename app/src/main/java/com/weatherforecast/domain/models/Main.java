package com.weatherforecast.domain.models;

import com.google.gson.annotations.SerializedName;

public class Main {
    private double temp;
    @SerializedName("feels_like")
    private double feelsLike;
    private double temp_min;
    private double temp_max;
    private long pressure;
    private long humidity;
    @SerializedName("sea_level")
    private long seaLevel;
    @SerializedName("grnd_level")
    private long grndLevel;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double value) {
        this.temp = value;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double value) {
        this.feelsLike = value;
    }

    public double getTempMin() {
        return temp_min;
    }

    public void setTempMin(double value) {
        this.temp_min = value;
    }

    public double getTempMax() {
        return temp_max;
    }

    public void setTempMax(double value) {
        this.temp_max = value;
    }

    public long getPressure() {
        return pressure;
    }

    public void setPressure(long value) {
        this.pressure = value;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long value) {
        this.humidity = value;
    }

    public long getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(long value) {
        this.seaLevel = value;
    }

    public long getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(long value) {
        this.grndLevel = value;
    }
}