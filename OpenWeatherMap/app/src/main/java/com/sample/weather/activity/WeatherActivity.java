package com.sample.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sample.weather.R;
import com.sample.weather.fragment.SearchWeatherFragment;
import com.sample.weather.fragment.WeatherResultFragment;
import com.sample.weather.handler.DataStoreHandler;
import com.sample.weather.model.WeatherSearchResponse;

/**
 * Displays weather information, contains two fragment search and result fragment
 * Created by VimalRaj on 11/12/2017.
 */
public class WeatherActivity extends AppCompatActivity implements SearchWeatherFragment.OnSearchListener {

    private TextView noResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        noResultView =  (TextView) findViewById(R.id.txtNoResult);
    }

    @Override
    public void onSearchResult(String query,WeatherSearchResponse searchResult) {
        WeatherResultFragment fragment =  (WeatherResultFragment)getSupportFragmentManager().findFragmentById(R.id.resultFragment);
        if (searchResult !=null && searchResult.getWeatherList() !=null){
            DataStoreHandler.saveSearchQuery(this,query);
            fragment.paintResponseData(searchResult);
            noResultView.setVisibility(View.GONE);
        } else if(searchResult !=null && searchResult.getCode().equals("404")){
            fragment.clearData();
            noResultView.setVisibility(View.VISIBLE);
            noResultView.setText(searchResult.getMessage());
        }else {
            fragment.clearData();
            noResultView.setVisibility(View.VISIBLE);
            noResultView.setText(getString(R.string.no_records));
        }
    }
}
