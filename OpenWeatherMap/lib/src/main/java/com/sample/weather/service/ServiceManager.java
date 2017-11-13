package com.sample.weather.service;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
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
 * Created by VimalRaj on 11/12/2017.
 */

public class ServiceManager {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?";

    public static WeatherSearchResponse retrieveWeather(@NonNull Map<String, String> params){
        OkHttpClient client = new OkHttpClient();
        String queryParams = convertToRequestQueryParams(params);
        Request request = new Request.Builder().
                url(URL+queryParams).build();
        try{
            Response response = client.newCall(request).execute();
            if (response !=null){
                return convertStringToObject(response.body().toString());
            }
        }catch (IOException ioe){
            throw new ServiceException(ioe);
        }
        return null;
    }

    private static WeatherSearchResponse convertStringToObject(String jsonString){
        Gson gson = new Gson();
        return gson.fromJson(jsonString,WeatherSearchResponse.class);
    }

    /**
     * Converts the map to request query params.
     * @param params
     * @return
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
