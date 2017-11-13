package com.sample.weather.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sample.weather.exception.ServiceException;
import com.sample.weather.model.WeatherSearchResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Service calls connects the web api call and performs the operation.
 * Created by VimalRaj on 11/12/2017.
 */

public class ServiceManager {

    //open weather map api url.
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?";

    /**
     * Retrieves weather information by performing service calls to openweathermap api.
     *
     * @param params - City name
     * @return WeatherSearchResponse
     */
    public static WeatherSearchResponse retrieveWeather(@NonNull Map<String, String> params) {
        OkHttpClient client = new OkHttpClient();
        String queryParams = convertToRequestQueryParams(params);
        Request request = new Request.Builder().
                url(URL + queryParams).build();
        try {
            Response response = client.newCall(request).execute();
            if (response != null) {
                return convertStringToObject(response.body().string());
            }
        } catch (IOException ioe) {
            throw new ServiceException(ioe);
        }
        return null;
    }

    /**
     * Converts the json string to response object.
     *
     * @param jsonString - json
     * @return WeatherSearchResponse
     */
    private static WeatherSearchResponse convertStringToObject(String jsonString) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, WeatherSearchResponse.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts the map to request query params.
     *
     * @param params - queryParams
     * @return - concat queryParams
     */
    private static String convertToRequestQueryParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            try {
                sb.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().substring(1);
    }
}
