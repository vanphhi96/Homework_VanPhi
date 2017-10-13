package com.example.vanph.greatstory.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.vanph.greatstory.adapters.AssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanph on 07/10/2017.
 */

public class DatabaseHandle {
    private AssetHelper assetHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String TAG = DatabaseHandle.class.toString();
    private DatabaseHandle(Context context)
    {
        assetHelper = new AssetHelper(context);
    }
    public  static  DatabaseHandle databaseHandle;
    public static DatabaseHandle getDatabaseHandle(Context context)
    {
        if(databaseHandle==null)
        {
            return  databaseHandle = new DatabaseHandle(context);
        }
        return databaseHandle;
    }
    public List<StoryModel> getListStory()
    {
        List<StoryModel> storyModelList = new ArrayList<>();
        sqLiteDatabase = assetHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_short_story",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            // get data;
            String image = cursor.getString(1);
            String title = cursor.getString(2);
            String description = cursor.getString(3);
            String content = cursor.getString(4);
            String author = cursor.getString(5);
            boolean bookmark = cursor.getInt(6)!=0;
            int id = cursor.getInt(0);
            StoryModel storyModel = new StoryModel(image,title,description,content,author,bookmark);
            storyModel.setId(id);
            storyModelList.add(storyModel);
            Log.d(TAG,"getListStory: "+storyModel.toString());

            //next
            cursor.moveToNext();
        }
        return storyModelList;
    }
    public void Update(int id, int bookmark){
        String sql = "UPDATE tbl_short_story SET bookmark = "+bookmark+" WHERE id = "+id;
        sqLiteDatabase.execSQL(sql);
    }
    public int getBookmark(int id){
        int bookmark =0;
        String sql = "SELECT bookmark FROM tbl_short_story WHERE id = "+id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        bookmark= cursor.getInt(0);
        return bookmark;
    }

 }
