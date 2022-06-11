package com.weatherforecast.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Welcome {
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private long visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;

    private long timezone;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private long cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord value) {
        this.coord = value;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> value) {
        this.weather = value;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String value) {
        this.base = value;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main value) {
        this.main = value;
    }

    public long getVisibility() {
        return visibility;
    }

    public void setVisibility(long value) {
        this.visibility = value;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind value) {
        this.wind = value;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds value) {
        this.clouds = value;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long value) {
        this.dt = value;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys value) {
        this.sys = value;
    }

    public long getTimezone() {
        return timezone;
    }

    public void setTimezone(long value) {
        this.timezone = value;
    }

    public long getID() {
        return id;
    }

    public void setID(long value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long value) {
        this.cod = value;
    }
}
