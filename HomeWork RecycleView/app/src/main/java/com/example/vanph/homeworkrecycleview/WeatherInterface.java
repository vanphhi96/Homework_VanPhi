package com.example.vanph.homeworkrecycleview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vanph on 17/11/2017.
 */

public interface WeatherInterface {
    @GET(Constant.GET_CURRENT_WEATHER)
    Call<WeatherResponseJSON> getWeather(
            @Query("q") String city,
            @Query("appid") String APPID
    );
}
