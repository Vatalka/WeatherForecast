package com.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
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

    private int orientation;

    // объявляю TextView
    TextView tvForecast;

    // объявляю экземпляр интерфейса WeatherApi
    WeatherApi weatherApi;

    // объявляю объект класса CompositeDisposable, который реализует интерфейс Disposable (RxJava3)
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orientation = Configuration.ORIENTATION_PORTRAIT;

        // инициализирую TextView
        tvForecast = findViewById(R.id.tvForecast);

        // создаю перехватчик OkHttp, который регистрирует информацию о запросах и ответах
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // строю экземпляр библиотеки Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/") // базовый Url сайта
                .client(client) // перехватчик OkHttp
                .addConverterFactory(GsonConverterFactory.create()) // конвертер JSON объектов
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // фабрика для корректного маппинга ответа
                .build();

        // использую объект retrofit для создания тела метода интерфейса JsonPlaceHolderApi
        weatherApi = retrofit.create(WeatherApi.class);

        callForecast();
    }

    public void callForecast() {
        disposable.add(weatherApi.getWelcomes()
                .subscribeOn(Schedulers.io()) // задает поток для метода Observable.create()
                .observeOn(AndroidSchedulers.mainThread()) // задаёт поток, на котором выполняются следующие операторы
                .subscribe(welcome -> {
                    // получаю значения из model
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
        disposable.clear();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        orientation = newConfig.orientation;
        setBackgroundImage(orientation);
    }

    @SuppressLint("ResourceType")
    private void setBackgroundImage(final int orientation) {

        ConstraintLayout layout;
        layout = findViewById(R.id.myLayout);

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