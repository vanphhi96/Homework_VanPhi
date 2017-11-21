package com.example.vanph.homeworkrecycleview;

import java.util.List;

/**
 * Created by vanph on 17/11/2017.
 */

public class WeatherResponseJSON {
    public List<Obj> list;
    public class Obj{
        public Temp temp;
        public  float humidity;
        public List<Weather> weather;
        public class Temp {
            public float day;
        }
        public class Weather{
            public  String main;
            public String description;
        }
    }


}
