package com.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.weatherforecast.api.model.Welcome;

import java.util.List;
import java.util.function.BiConsumer;

import io.reactivex.disposables.CompositeDisposable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();

    TextView tvForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvForecast = findViewById(R.id.tvForecast);

        App app = (App) getApplication();

        disposable.add(app.getWeatherService().getApi().getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<List<Welcome>, Throwable>() {
                    @Override
                    public void accept(List<Welcome> weather, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_LONG).show();
                        } else {
                            tvForecast.setWeather(weather);
                        }
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}