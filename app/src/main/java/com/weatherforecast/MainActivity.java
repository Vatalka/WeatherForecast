package com.weatherforecast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.weatherforecast.api.model.Welcome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Welcome> welcomes;
    TextView tvForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvForecast = findViewById(R.id.tvForecast);
        welcomes = new ArrayList<>();

        try {
            Response response = App.getApi().getWelcome().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        App.getApi().getWelcome().enqueue(new Callback<List<Welcome>>() {
            @Override
            public void onResponse(Call<List<Welcome>> call, Response<List<Welcome>> response) {
                welcomes.addAll(response.body());
                tvForecast.setText();
            }

            @Override
            public void onFailure(Call<List<Welcome>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Data loading error", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}