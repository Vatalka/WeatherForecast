package com.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvForecast;

    // The Interface instance
    WeatherApi weatherApi;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the textView
        tvForecast = findViewById(R.id.tvForecast);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // Building a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        // Use the retrofit instance to create the method body of JsonPlaceHolderApi Interface
        weatherApi = retrofit.create(WeatherApi.class);

        callForecast();
    }

    public void callForecast() {
        disposable.add(weatherApi.getWelcomes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(welcome -> {
                    // Get the values
                    String content = "";
                    content += "Страна: " + welcome.getSys().getCountry() + "\n";
                    content += "Населённый пункт: " + welcome.getName() + "\n";
                    content += "Температура: " + welcome.getMain().getTemp() + " °C" + "\n";
                    content += "Давление: " + welcome.getMain().getPressureMm() + " мм.рт.ст." + "\n";
                    content += "Облака: " + welcome.getClouds().getAll() + " %" + "\n";
                    content += "Влажность: " + welcome.getMain().getHumidity() + " %" + "\n";
                    content += "Видимость: " + welcome.getVisibility() + " метров" + "\n";
                    content += "Скорость ветра: " + welcome.getWind().getSpeed() + " м/с." + "\n";
                    content += "Рассвет: " + welcome.getSys().getSunriseTime() + "\n";
                    content += "Закат: " + welcome.getSys().getSunsetTime() + "\n";

                    tvForecast.setText(content);
                }));
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}