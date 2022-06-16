package com.weatherforecast.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("feelsLike")
    @Expose
    private double feelsLike;
    @SerializedName("tempMin")
    @Expose
    private double tempMin;
    @SerializedName("tempMax")
    @Expose
    private double tempMax;
    @SerializedName("pressure")
    @Expose
    private long pressure;
    @SerializedName("humidity")
    @Expose
    private long humidity;
    @SerializedName("seaLevel")
    @Expose
    private long seaLevel;
    @SerializedName("grndLevel")
    @Expose
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
        return tempMin;
    }

    public void setTempMin(double value) {
        this.tempMin = value;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double value) {
        this.tempMax = value;
    }

    public long getPressure() {return pressure;}

    public void setPressure(long value) {this.pressure = value;}

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

    // давление в мм.рт.ст.
    public double getPressureMm() {
        return pressure * 0.750062;
    }
}