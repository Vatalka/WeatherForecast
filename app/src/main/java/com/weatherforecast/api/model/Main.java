package com.weatherforecast.api.model;

public class Main {
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private long pressure;
    private long humidity;
    private long seaLevel;
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