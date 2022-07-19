package com.weatherforecast.domain.converter;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("DefaultLocale")
public class Converter {

    private static final double PRESSURE_MULTIPLAYER = 0.750062;
    @SuppressLint("ConstantLocale")
    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "dd:MM:yyyy; HH:mm:ss", Locale.getDefault());

    // конвертирую hPa в мм рт.ст.
    public static String hPaToMmHg(long value) {
        String formattedDouble = String.format("%.2f", (double) value * PRESSURE_MULTIPLAYER);
        return formattedDouble + " мм рт.ст.";
    }

    // конвертирую Unix-время в дату
    public static String unixTimeToDate(long value){
        Date date = new Date(value * 1000L);
        return sdf.format(date);
    }

    // конвертирую метры в километры
    public static String metersToKm(long value) {
        String formattedDouble = String.format("%.2f", (double) value / 1000);
        return formattedDouble + " км";
    }
}
