package com.example.helprefrigerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static DbHelper sInstance; // 1개의 instance만
    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "list.db"; // File name
    private static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                    List.Entry.TABLE_NAME,
                    List.Entry._ID,
                    List.Entry.COLUMN_NAME_ID,
                    List.Entry.COLUMN_NAME_PW,
                    List.Entry.COLUMN_NAME_NAME,
                    List.Entry.COLUMN_NAME_DATE,
                    List.Entry.COLUMN_NAME_AMOUNT);

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXITS " + List.Entry.TABLE_NAME;

    public static DbHelper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new DbHelper (context);
        } return sInstance;
    }

    public DbHelper(@Nullable Context context) {
        super (context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL (SQL_DELETE_ENTRIES);
        onCreate (db);
    }
}
