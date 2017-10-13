package com.example.vanph.greatstory.acitivities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vanph.greatstory.R;
import com.example.vanph.greatstory.databases.DatabaseHandle;
import com.example.vanph.greatstory.databases.StoryModel;

public class DescriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivBookmark;
    private ImageView ivStory;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvDesciption;
    private Button btnReading;
    private StoryModel storyModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        setupUI();
        addListenner();
        loadData();
    }

    @Override
    protected void onStart() {
        setColorBookmark();
        super.onStart();
    }

    private void loadData() {
        storyModel = (StoryModel) getIntent().getSerializableExtra(MainActivity.STORY_KEY);
        String[] base64 = storyModel.getImage().split(",");
        Bitmap bitmap = BitmapFactory.decodeByteArray(
                Base64.decode(base64[1],Base64.DEFAULT),0,
                (Base64.decode(base64[1],Base64.DEFAULT)).length
        );
        ivStory.setImageBitmap(bitmap);
        tvTitle.setText(storyModel.getTitle());
        tvAuthor.setText(storyModel.getAuthor());
        tvDesciption.setText(storyModel.getDescription());
    }

    public void setupUI()
    {
        btnReading= (Button) findViewById(R.id.btn_reading);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBookmark = (ImageView) findViewById(R.id.iv_bookmark);
        ivStory = (ImageView) findViewById(R.id.iv_story);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAuthor = (TextView) findViewById(R.id.tv_author);
        tvDesciption = (TextView) findViewById(R.id.tv_description);
    }
    public void addListenner()
    {
        ivBack.setOnClickListener(this);
        ivBookmark.setOnClickListener(this);
        btnReading.setOnClickListener(this) ;
    }
    public void setBookmark()
    {
        int bookmark = 0;
        if(!storyModel.getBookmark()){
            bookmark=1;
            ivBookmark.setColorFilter(Color.rgb(255,204,0));
            storyModel.setBookmark(true);
        }
        else{
            ivBookmark.setColorFilter(Color.WHITE);
            storyModel.setBookmark(false);
            bookmark=0;
        }
        DatabaseHandle.getDatabaseHandle(this).Update(storyModel.getId(),bookmark);
    }
    public void setColorBookmark()
    {
        if(!storyModel.getBookmark()){
            ivBookmark.setColorFilter(Color.WHITE);

        }
        else{
            ivBookmark.setColorFilter(Color.rgb(255,204,0));

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                super.onBackPressed();
                break;
            case R.id.iv_bookmark:
                setBookmark();
                break;
            case R.id.btn_reading:

                break;
        }
    }
}
