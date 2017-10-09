package com.example.vanph.homework5;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private FloatingActionButton addNew;
    private ListView lvNotes;
    private List<NoteModel> noteModelList = new ArrayList<>();
    private  MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new MyDBHelper(MainActivity.this);
        setupUI();
        addListtenners();
    }

    public void setupUI()
    {
        addNew = (FloatingActionButton) findViewById(R.id.btn_add);
        lvNotes = (ListView) findViewById(R.id.lv_notes);
        LoadView();
    }
    public void LoadView()
    {
        noteModelList = dbHelper.getListStory();
        NoteAdapter noteAdapter = new NoteAdapter(this,R.layout.items_listnote,noteModelList);
        lvNotes.setAdapter(noteAdapter);
    }
    private void addListtenners() {

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, writenote.class);
                startActivity(intent);
                LoadView();
            }
        });


    }
}
