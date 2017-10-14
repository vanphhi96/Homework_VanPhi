package com.example.vanph.homework5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanph on 09/10/2017.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    private String TAG = MyDBHelper.class.toString();

    private static final String DATABASE_NAME = "note.db";
    private static final int DATA_VERSION = 1;

    public static final String TABLE_NAME = "tbl_note";
    public static final String COL_TITLE= "title";
    public static final String COL_description = "description";
    private static final String STRING_CREATE = "CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_TITLE+" TEXT, "+COL_description+" TEXT);";
    private  Context context;

    public MyDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATA_VERSION);

        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"onCreate" +db.toString());
        db.execSQL(STRING_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS tbl_note;");
        onCreate(db);
    }
    public List<NoteModel> getListStory()
    {
        List<NoteModel> storyModelList = new ArrayList<>();

        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM tbl_note",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            // get data;
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            NoteModel noteModel = new NoteModel(title,description);
            noteModel.setId(id);
            storyModelList.add(noteModel);
            Log.d(TAG,"getListStory: "+noteModel.toString());

            //next
            cursor.moveToNext();
        }
        return storyModelList;
    }
    public int getID()
    {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT MAX(id) FROM tbl_note",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            return cursor.getInt(0)+1;
        }
        return 0;
    }
    public void addNote(NoteModel note){


        ContentValues values = new ContentValues();
       // values.put("id",getID());
        values.put(COL_TITLE, note.getTitle());
        values.put(COL_description, note.getDescription());
        Log.d(TAG,"addNote"+note.getTitle()+" id= "+ getID());
        this.getWritableDatabase().insert(TABLE_NAME, null, values);
    }
    public void Update(NoteModel note)
    {
        this.getWritableDatabase().execSQL("UPDATE "+TABLE_NAME+" SET "+COL_TITLE+ " = '"+note.getTitle()+"', "+COL_description+" = '"+note.getDescription()+"' WHERE id = "+note.getId());
    }
   public void deleteNote(int id)
   {
       this.getWritableDatabase().execSQL("DELETE FROM tbl_note WHERE id = "+id);
   }


}
