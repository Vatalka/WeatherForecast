package com.weatherforecast.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    @Expose
    private long lon;
    @SerializedName("lat")
    @Expose
    private long lat;

    public long getLon() {
        return lon;
    }

    public void setLon(long value) {
        this.lon = value;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long value) {
        this.lat = value;
    }
}
