package com.example.vanph.homework5;

import android.app.Activity;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class writenote extends AppCompatActivity {
    private static final String TAG = writenote.class.toString();
    private ImageView iv_done;
    private ImageView iv_back;
    private EditText et_title;
    private  EditText et_description;
    private MyDBHelper myDBHelper;
    public static int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writenote);
        myDBHelper = new MyDBHelper(this);
        setupUI();
        addListtenners();
    }
    public void setupUI()
    {
        iv_done = (ImageView) findViewById(R.id.iv_done);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_title= (EditText) findViewById(R.id.et_title);
        et_description= (EditText) findViewById(R.id.et_description);

    }
    public void addNote()
    {
        String title;
        String desciption;
        title=et_title.getText().toString();
        desciption=et_description.getText().toString();
        if(title.matches("")&&desciption.matches(""))
        {
            NoteModel abc = new NoteModel(title,desciption);
            myDBHelper.addNote(abc);
            Toast.makeText(this,"Save",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(this,"Not saved",Toast.LENGTH_SHORT);
        }


    }
    public void addListtenners()
    {
        iv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNote();
                iv_done.setClickable(false);
                Log.d(TAG,"add successfully");
                finish();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
