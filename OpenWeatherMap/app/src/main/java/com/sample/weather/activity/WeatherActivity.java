package com.sample.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sample.weather.R;

public class WeatherActivity extends AppCompatActivity {

    private TextView noResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        noResultView =  (TextView) findViewById(R.id.txtNoResult);
    }


}
