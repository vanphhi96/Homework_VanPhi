package com.example.vanph.homeworkrecycleview;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vanph on 17/11/2017.
 */

public class RetrofitInstance {
    public static Retrofit retrofit;
    public static  Retrofit getInstace()
    {
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
