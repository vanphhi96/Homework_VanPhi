package com.example.vanph.greatstory.acitivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.vanph.greatstory.databases.DatabaseHandle;
import com.example.vanph.greatstory.R;
import com.example.vanph.greatstory.adapters.StoryAdapter;
import com.example.vanph.greatstory.databases.StoryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<StoryModel> storyModelList = new ArrayList<>();
    private ListView lvStory;
    public static final String STORY_KEY="story_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvStory = (ListView) findViewById(R.id.lv_story);


    }

    @Override
    protected void onStart() {
        storyModelList = DatabaseHandle.getDatabaseHandle(this).getListStory();
        StoryAdapter storyAdapter = new StoryAdapter(this,R.layout.item_liststory,DatabaseHandle.getDatabaseHandle(this).getListStory());
        lvStory.setAdapter(storyAdapter);
        lvStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
                intent.putExtra(STORY_KEY,storyModelList.get(position));
                startActivity(intent);
            }
        });
        super.onStart();
    }
}
