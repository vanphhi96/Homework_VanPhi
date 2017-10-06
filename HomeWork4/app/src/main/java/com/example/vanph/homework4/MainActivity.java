package com.example.vanph.homework4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText screen;
    private String display="";
    private ImageView btngoi;
    public String  TAG = MainActivity.class.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setClickbtn();
    }
    public void updateScreen()
    {
        screen.setText(display);
    }
    public void onClickNumBer(View v)
    {
        Button b = (Button)(v);
        display+=b.getText();
        updateScreen();
    }
    public void setUI()
    {
        screen = (EditText) findViewById(R.id.et_screen);
        btngoi = (ImageView)findViewById(R.id.btn_goi);
    }
    public void setClickbtn()
    {
        btngoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d(TAG,display);
                Uri call =  Uri.parse("tel:"+display);
                Log.d(TAG,call.toString());
                Intent callIntent = new Intent(Intent.ACTION_CALL,call);
                if(callIntent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(callIntent);
                }

            }
        });
    }
}
