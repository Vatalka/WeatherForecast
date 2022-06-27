package com.weatherforecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.weatherforecast.api.WeatherApi;
import com.weatherforecast.api.model.Weather;
import com.weatherforecast.api.model.Welcome;

import java.util.List;

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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // фабрика для маппинга ответа
                .build();

        // использую объект retrofit для создания тела метода интерфейса JsonPlaceHolderApi
        weatherApi = retrofit.create(WeatherApi.class);

        callForecast();
    }

    public void callForecast() {
        disposable.add(weatherApi.getWelcomes()
                .subscribeOn(Schedulers.io()) // задает поток для метода Observable.create()
                .observeOn(AndroidSchedulers.mainThread()) // задаёт поток для следующих операторов
                .subscribe(welcome -> {
                    // получаю значения из model
                    String content = "";
                    content += "Координаты: \n"
                            + "     широта: " + welcome.getCoord().getLat() + "\n"
                            + "     долгота: " + welcome.getCoord().getLon() + "\n";
                    content += "Погода: " + getWeather(welcome) + "\n";
                    content += "База: " + welcome.getBase() + "\n";
                    content += "Температура: " + welcome.getMain().getTemp() + " °C\n"
                            + "     чувствуется как: " + welcome.getMain().getFeelsLike() + " °C\n"
                            + "     минимальная: " + welcome.getMain().getTempMin() + " °C\n"
                            + "     максимальная: " + welcome.getMain().getTempMax() + " °C\n";
                    content += "Давление: " + Converter.hPaToMmHg(welcome.getMain().getPressure()) + "\n"
                            + "     на уровне моря: " + Converter.hPaToMmHg(welcome.getMain().getSeaLevel()) + "\n"
                            + "     на уровне земли: " + Converter.hPaToMmHg(welcome.getMain().getGrndLevel()) + "\n";
                    content += "Влажность: " + welcome.getMain().getHumidity() + " %\n";
                    content += "Видимость: " + Converter.metersToKm(welcome.getVisibility()) + "\n";
                    content += "Скорость ветра: " + welcome.getWind().getSpeed() + " м/с\n";
                    content += "     направление: " + welcome.getWind().getDeg() + "°\n";
                    content += "     порывы: " + welcome.getWind().getGust() + " м/с\n";
                    content += "Облака: " + welcome.getClouds().getAll() + " %\n";
                    content += "Время расчёта данных: " + Converter.unixTimeToDate(welcome.getDt()) + "\n";
                    content += "Страна: " + welcome.getSys().getCountry() + "\n";
                    content += "Рассвет: " + Converter.unixTimeToDate(welcome.getSys().getSunrise()) + "\n";
                    content += "Закат: " + Converter.unixTimeToDate(welcome.getSys().getSunset()) + "\n";
                    content += "Часовой пояс: " + welcome.getTimezone() + " сек\n";
                    content += "id города: " + welcome.getID() + "\n";
                    content += "Город: " + welcome.getName() + "\n";
                    content += "cod: " + welcome.getCod() + "\n";

                    tvForecast.setText(content);
                }));
    }

    private String getWeather(Welcome welcome) {
        if (welcome.getWeather().size() > 0) {
            return welcome.getWeather().get(0).toString();
        }
        return "";
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