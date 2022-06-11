package com.weatherforecast.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String value) {
        this.main = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String value) {
        this.icon = value;
    }
}
