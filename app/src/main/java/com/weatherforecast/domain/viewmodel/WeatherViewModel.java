package com.weatherforecast.domain.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.weatherforecast.domain.models.Forecast;
import com.weatherforecast.domain.repository.WeatherRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherViewModel extends ViewModel {

    private final MutableLiveData<Forecast> _FORECAST = new MutableLiveData<>();
    private final LiveData<Forecast> FORECAST = _FORECAST;
    private final CompositeDisposable DISPOSABLE = new CompositeDisposable();

    public LiveData<Forecast> getFORECAST() {
        return FORECAST;
    }

    public WeatherViewModel() {

        final WeatherRepository WEATHER_REPOSITORY = new WeatherRepository();

        DISPOSABLE.add(WEATHER_REPOSITORY.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            _FORECAST.setValue(result);
                        }, error -> {
                            error.printStackTrace();
                        }));
    }

    @Override
    protected void onCleared() {
        DISPOSABLE.clear();
        super.onCleared();
    }
}
