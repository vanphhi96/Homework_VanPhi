package com.example.vanph.homeworkthoitiet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vanph on 17/11/2017.
 */

public interface WeatherInterface {
    @GET("weather")
    Call<WeatherResponseJSON> getWeather(
            @Query("q") String city,
            @Query("appid") String APPID
    );
}
