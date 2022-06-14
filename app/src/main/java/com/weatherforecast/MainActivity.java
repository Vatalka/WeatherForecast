package com.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.weatherforecast.api.model.Welcome;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvForecast;

    // The Interface instance
    WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the textView
        tvForecast = findViewById(R.id.tvForecast);

        // Building a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Use the retrofit instance to create the method body of JsonPlaceHolderApi Interface
        weatherApi = retrofit.create(WeatherApi.class);

        getUser();
    }

    public void getUser() {
        // Execute the Network request
        Call<Welcome> call = weatherApi.getWelcomes();

        // Execute the request in a background thread
        call.enqueue(new Callback<Welcome>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Welcome> call, @NonNull Response<Welcome> response) {
                if (!response.isSuccessful()) {
                    tvForecast.setText("Code: " + response.code());
                    return;
                }

                // Get the values
                String content = "";
                assert response.body() != null;
                content += "Страна: " + response.body().getSys().getCountry() + "\n";
                content += "Населённый пункт: " + response.body().getName() + "\n";
                content += "Температура: " + response.body().getMain().getTemp() + " °C" + "\n";
                content += "Давление: " + response.body().getMain().getPressureMm() + " мм.рт.ст." + "\n";
                content += "Облака: " + response.body().getClouds().getAll() + " %" +  "\n";
                content += "Влажность: " + response.body().getMain().getHumidity() + " %" + "\n";
                content += "Видимость: " + response.body().getVisibility() + " метров" + "\n";
                content += "Скорость ветра: " + response.body().getWind().getSpeed() + " м/с." + "\n";
                content += "Рассвет: " + response.body().getSys().getSunriseTime() + "\n";
                content += "Закат: " + response.body().getSys().getSunsetTime() + "\n";

                tvForecast.setText(content);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<Welcome> call, @NonNull Throwable t) {
                tvForecast.setText("Failure: " + t);
            }
        });
    }
}