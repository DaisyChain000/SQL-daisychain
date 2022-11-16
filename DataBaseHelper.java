package com.eyes.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "htetlin.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "_student";
    SQLiteDatabase database;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table
        String query = "CREATE Table " + TABLE_NAME + "(id Integer primary key autoincrement ,name text,email Text," +
                "moblile Text);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists " + TABLE_NAME);
        onCreate(db);

    }

    public long saveData(String id, String nameText, String emailText, String mobileText) {
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", nameText);
        contentValues.put("email", emailText);
        contentValues.put("mobile", mobileText);
        return database.insert(TABLE_NAME, null, contentValues);

    }

}
