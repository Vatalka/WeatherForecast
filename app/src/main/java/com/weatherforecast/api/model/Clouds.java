package com.weatherforecast.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    @Expose
    private long all;

    public long getAll() {
        return all;
    }

    public void setAll(long value) {
        this.all = value;
    }
}
