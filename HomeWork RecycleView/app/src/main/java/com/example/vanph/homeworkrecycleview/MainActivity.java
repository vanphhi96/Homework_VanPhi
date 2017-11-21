package com.example.vanph.homeworkrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.toString();
    public SearchView searchView;
    public RecyclerView recyclerView;
    public List<WeatherModel> weatherModels = new ArrayList<>();
    public RecycleAdapter recycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search);
        recyclerView = findViewById(R.id.rv_danhsach);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                WeatherInterface weatherInterface = RetrofitInstance.getInstace().create(WeatherInterface.class);
                weatherInterface.getWeather(s,Constant.APP_ID).enqueue(new Callback<WeatherResponseJSON>() {

                    @Override
                    public void onResponse(Call<WeatherResponseJSON> call, Response<WeatherResponseJSON> response) {
                        weatherModels = new ArrayList<>();
                        if(response.isSuccessful())
                        {

                            List<WeatherResponseJSON.Obj> lst = response.body().list;
                            //Toast.makeText(MainActivity.this,"size"+lst.size()+" ",Toast.LENGTH_LONG).show();
                            for(int i=0; i<lst.size(); i++)
                            {
                                WeatherModel weatherModel = new WeatherModel();
                                if(lst.get(i).weather.get(0).main.equals("Rain"))
                                {
                                    if(lst.get(i).weather.get(0).description.equals("heavy intensity rain"))// mua nang hat
                                    {
                                        weatherModel.imageID = MainActivity.this.getResources().getIdentifier("weather_8","raw",
                                                MainActivity.this.getPackageName());
                                        weatherModel.mota="Mưa nặng hạt";
                                    }
                                    else if(lst.get(i).weather.get(0).description.equals("light rain"))//mua nho
                                    {
                                        weatherModel.imageID = MainActivity.this.getResources().getIdentifier("weather_6","raw",
                                                MainActivity.this.getPackageName());
                                        weatherModel.mota="Mưa nhỏ";
                                    }
                                    else{
                                        weatherModel.imageID = MainActivity.this.getResources().getIdentifier("weather_7","raw",
                                                MainActivity.this.getPackageName());
                                        weatherModel.mota="Mưa";
                                    }

                                } else if(lst.get(i).weather.get(0).main.equals("Clouds"))
                                {
                                    weatherModel.imageID = MainActivity.this.getResources().getIdentifier("weather_5","raw",
                                            MainActivity.this.getPackageName());
                                    weatherModel.mota="Có mây";
                                }
                                    else
                                {
                                    if(lst.get(i).weather.get(0).description.equals("sky is clear"))// troi nang
                                    {
                                        weatherModel.imageID = MainActivity.this.getResources().getIdentifier("weather_1","raw",
                                                MainActivity.this.getPackageName());
                                        weatherModel.mota="Trời nắng, không có mây";
                                    }
                                    else{
                                        weatherModel.imageID = MainActivity.this.getResources().getIdentifier("weather_2","raw",
                                                MainActivity.this.getPackageName());
                                        weatherModel.mota="Trời nắng, có mây";
                                    }
                                }
                                weatherModel.temp = lst.get(i).temp.day;
                                weatherModel.doam= lst.get(i).humidity;
                                weatherModel.ngay= i+1;
                                weatherModels.add(weatherModel);
                            }
                            recycleAdapter = new RecycleAdapter(weatherModels);
                            recyclerView.setAdapter(recycleAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recycleAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Không tìm được thời tiết thành phố này",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponseJSON> call, Throwable t) {

                    }
                });

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
}
