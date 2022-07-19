package com.weatherforecast.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.weatherforecast.R;
import com.weatherforecast.domain.converter.Converter;
import com.weatherforecast.domain.models.Forecast;
import com.weatherforecast.domain.retrofit.RetrofitClient;
import com.weatherforecast.domain.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private final WeatherViewModel WEATHER_VIEW_MODEL = new WeatherViewModel();

    private int orientation;

    // обьявляю Button
    Button btnWeather;

    // объявляю TextView
    TextView tvForecast;
    TextView tvWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orientation = Configuration.ORIENTATION_PORTRAIT;

        // инициализирую Button
        btnWeather = findViewById(R.id.btnWeather);

        // инициализирую TextView
        tvForecast = findViewById(R.id.tvForecast);
        tvWebsite = findViewById(R.id.tvWebsite);

        // обрабатываю нажатие кнопки
        btnWeather.setOnClickListener(v -> {
            tvWebsite.setText(RetrofitClient.BASE_URL);
            WEATHER_VIEW_MODEL.getFORECAST().observe(this, forecast -> {

                // получаю значения из model
                String content = "";
                content += "Координаты: \n"
                        + "     широта: " + forecast.getCoord().getLat() + "\n"
                        + "     долгота: " + forecast.getCoord().getLon() + "\n";
                content += "Погода: " + getWeatherModel(forecast) + "\n";
                content += "База: " + forecast.getBase() + "\n";
                content += "Температура: " + forecast.getMain().getTemp() + " °C\n"
                        + "     чувствуется как: " + forecast.getMain().getFeelsLike() + " °C\n"
                        + "     минимальная: " + forecast.getMain().getTempMin() + " °C\n"
                        + "     максимальная: " + forecast.getMain().getTempMax() + " °C\n";
                content += "Давление: " + Converter.hPaToMmHg(forecast.getMain().getPressure()) + "\n"
                        + "     на уровне моря: " + Converter.hPaToMmHg(forecast.getMain().getSeaLevel()) + "\n"
                        + "     на уровне земли: " + Converter.hPaToMmHg(forecast.getMain().getGrndLevel()) + "\n";
                content += "Влажность: " + forecast.getMain().getHumidity() + " %\n";
                content += "Видимость: " + Converter.metersToKm(forecast.getVisibility()) + "\n";
                content += "Скорость ветра: " + forecast.getWind().getSpeed() + " м/с\n";
                content += "     направление: " + forecast.getWind().getDeg() + "°\n";
                content += "     порывы: " + forecast.getWind().getGust() + " м/с\n";
                content += "Облака: " + forecast.getClouds().getAll() + " %\n";
                content += "Время расчёта данных: " + Converter.unixTimeToDate(forecast.getDt()) + "\n";
                content += "Страна: " + forecast.getSys().getCountry() + "\n";
                content += "Рассвет: " + Converter.unixTimeToDate(forecast.getSys().getSunrise()) + "\n";
                content += "Закат: " + Converter.unixTimeToDate(forecast.getSys().getSunset()) + "\n";
                content += "Часовой пояс: " + forecast.getTimezone() + " сек\n";
                content += "id города: " + forecast.getID() + "\n";
                content += "Город: " + forecast.getName() + "\n";
                content += "cod: " + forecast.getCod() + "\n";

                tvForecast.setText(content);
            });
        });
    }

    private String getWeatherModel(Forecast forecast) {
        if (forecast.getWeather().size() > 0) {
            return forecast.getWeather().get(0).toString();
        }
        return "";
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        orientation = newConfig.orientation;
        setBackgroundImage(orientation);
    }

    private void setBackgroundImage(final int orientation) {
        ConstraintLayout layout = findViewById(R.id.myLayout);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            layout.setBackgroundResource(R.drawable.background_weather_forecast_19201080);
        else if (orientation == Configuration.ORIENTATION_PORTRAIT)
            layout.setBackgroundResource(R.drawable.background_weather_forecast_10801920);
    }

    @Override
    public void onResume() {
        super.onResume();
        setBackgroundImage(orientation);
    }
}