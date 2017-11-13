package com.sample.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VimalRaj on 11/12/2017.
 */

public class WeatherSearchResponse {

    @SerializedName("icon")
    String icon;

    @SerializedName("description")
    String description;

    @SerializedName("dt")
    long date;

    @SerializedName("temp")
    float temperature;

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public long getDate() {
        return date;
    }

    public float getTemperature() {
        return temperature;
    }
}
