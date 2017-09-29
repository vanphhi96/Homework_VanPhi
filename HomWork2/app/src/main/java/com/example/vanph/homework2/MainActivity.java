package com.example.vanph.homework2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView img_add;
    private LinearLayout ln_imageView;
    private int min =1;
    private int max=5;
    private int weightsum =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_add = (ImageView) findViewById(R.id.img_add);
        ln_imageView = (LinearLayout) findViewById(R.id.ln_imageView);
        addListtenners();
    }

    private void addListtenners() {

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rd = new Random();
                int range = max - min + 1;
                int randomNum = min + rd.nextInt(range);
                ln_imageView.setWeightSum(weightsum);
                int width = ln_imageView.getWidth();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width/2);
                layoutParams.setMargins(0,10,0,10);
                ImageView abc = new ImageView(MainActivity.this);
                abc.setLayoutParams(layoutParams);
                String drawableName = "food_"+ randomNum;
                int resID = getResources().getIdentifier(drawableName, "drawable",  getPackageName());
                abc.setImageResource(resID);
                abc.setScaleType(ImageView.ScaleType.CENTER);

                ln_imageView.addView(abc);
                weightsum++;
            }
        });
    }

}
