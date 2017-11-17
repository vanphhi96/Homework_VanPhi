package com.example.vanph.homeworkthoitiet;

import java.util.List;

/**
 * Created by vanph on 17/11/2017.
 */

public class WeatherResponseJSON {
    public ObjectJSON main;
    public class ObjectJSON {
        float temp;
        float pressure;
        float humidity;
        float temp_min;
        float temp_max;
    }
}
