package com.example.vanph.homeworkthoitiet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView tv_nhietdo;
    TextView tv_doam;
    Button btn_tim;
    EditText ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_nhietdo = findViewById(R.id.tv_doam);
        tv_doam = findViewById(R.id.tv_nhietdo);
        btn_tim = findViewById(R.id.btn_tim);
        ten = findViewById(R.id.edit_ten);

        btn_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_doam.setText("Độ ẩm:");
                tv_nhietdo.setText("Nhiệt độ:");
                String vitri = ten.getText().toString();
                vitri = vitri.toLowerCase();
                 WeatherInterface weatherInterface = RetrofitInstance.getInstace().create(WeatherInterface.class);
                weatherInterface.getWeather(vitri,"c8d06a30627d02e23c0bc7f7f9e18108").enqueue(new Callback<WeatherResponseJSON>() {
                    @Override
                    public void onResponse(Call<WeatherResponseJSON> call, Response<WeatherResponseJSON> response) {
                        Log.d(TAG, "onResponse: ");
                        if(response.isSuccessful())
                        {
                            WeatherResponseJSON.ObjectJSON obj = response.body().main;
                            tv_nhietdo.setText("Nhiệt độ: "+Float.toString(Math.round(obj.temp-273))+" độ C");
                            tv_doam.setText("Độ ẩm: "+Float.toString(obj.humidity)+"%");
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherResponseJSON> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Không tìm thấy dữ liệu của thành phố này",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
