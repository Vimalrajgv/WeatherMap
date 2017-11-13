package com.sample.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Service response object.
 * Created by VimalRaj on 11/12/2017.
 */

public class WeatherSearchResponse {


    @SerializedName("dt")
    private long date;

    @SerializedName("weather")
    private List<Weather> weatherList;

    @SerializedName("main")
    private WeatherMain weatherMain;

    @SerializedName("cod")
    private String code;

    @SerializedName("message")
    private String message;

    public long getDate() {
        return date;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
