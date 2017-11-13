package com.sample.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Service response object.
 * Created by VimalRaj on 11/13/2017.
 */

public class WeatherMain {

    @SerializedName("temp")
    double temparature;

    @SerializedName("pressure")
    String pressure;

    @SerializedName("humidity")
    String humidity;

    @SerializedName("temp_min")
    String minTemp;

    @SerializedName("temp_max")
    String maxTemp;

    public double getTemparature() {
        return temparature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }
}
