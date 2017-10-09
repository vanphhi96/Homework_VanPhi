package com.example.vanph.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;

/**
 * Created by vanph on 08/10/2017.
 */

public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tbl_note.db";
    private static final int DATA_VERSION = 1;

    public static final String TABLE_NAME = "note";
    public static final String COL_TITLE= "title";
    public static final String COL_description = "description";
    private static final String STRING_CREATE = "CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COL_TITLE+" TEXT, "+COL_description+" TEXT);";

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATA_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STRING_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
