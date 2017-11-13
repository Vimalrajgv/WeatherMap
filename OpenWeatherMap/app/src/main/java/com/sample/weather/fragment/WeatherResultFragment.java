package com.sample.weather.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sample.weather.R;
import com.sample.weather.model.Weather;
import com.sample.weather.model.WeatherMain;
import com.sample.weather.model.WeatherSearchResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Result fragment view, displays the search result.
 * Created by VimalRaj on 11/12/2017.
 */

public class WeatherResultFragment extends Fragment {

    private TextView dateView;
    private TextView temperatureView;
    private TextView descriptionView;
    private TextView pressureView;
    private TextView humidityView;
    private ImageView iconView;
    private final String imageURL = "http://openweathermap.org/img/w/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content_weather, container, false);
        dateView = (TextView) view.findViewById(R.id.txtDate);
        temperatureView = (TextView) view.findViewById(R.id.txtTemperature);
        descriptionView = (TextView) view.findViewById(R.id.txtDescription);
        pressureView = (TextView) view.findViewById(R.id.txtPressure);
        humidityView = (TextView) view.findViewById(R.id.txtHumidity);
        iconView = (ImageView) view.findViewById(R.id.imgWeatherIcon);
        return view;
    }

    /**
     * Renders the UI with search response.
     *
     * @param response - Search response.
     */
    public void paintResponseData(WeatherSearchResponse response) {
        if (response != null) {
            List<Weather> weatherList = response.getWeatherList();
            if (weatherList != null && weatherList.size() > 0) {
                descriptionView.setText(weatherList.get(0).getDescription());
                String imgURL = imageURL + weatherList.get(0).getIcon() + ".png";
                Glide.with(this).load(imgURL).into(iconView);
            }
            WeatherMain main = response.getWeatherMain();
            String temp = String.valueOf(main.getTemparature()) + getString(R.string.fahrenheit);
            temperatureView.setText(temp);
            String pressure = getString(R.string.label_pressure)+main.getPressure();
            String humidity = getString(R.string.label_humidity)+main.getHumidity();
            pressureView.setText(pressure);
            humidityView.setText(humidity);
            dateView.setText(currentDate());
        }
    }

    /**
     * Clears UI when there is no result returned.
     */
    public void clearData() {
        descriptionView.setText("");
        temperatureView.setText("");
        humidityView.setText("");
        pressureView.setText("");
        dateView.setText("");
        iconView.setImageResource(0);
    }

    /**
     * Gets the current date to display.
     *
     * @return formatted date.
     */
    private String currentDate() {
        try {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("MMMM dd hh:mm a", Locale.US);
            return format.format(date);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
