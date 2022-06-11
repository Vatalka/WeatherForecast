package com.weatherforecast.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private long deg;
    @SerializedName("gust")
    @Expose
    private double gust;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double value) {
        this.speed = value;
    }

    public long getDeg() {
        return deg;
    }

    public void setDeg(long value) {
        this.deg = value;
    }

    public double getGust() {
        return gust;
    }

    public void setGust(double value) {
        this.gust = value;
    }
}
