package com.example.projetmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Form.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Form.TABLE_NAME + " (" +
                DBContract.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Form.COLUMN_USER + " TEXT," +
                DBContract.Form.COLUMN_PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Form.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public void insertSession(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        // insertion create a row and insert it
        ContentValues row=new ContentValues();
        row.put(DBContract.Form.COLUMN_USER, user);
        row.put(DBContract.Form.COLUMN_PASSWORD, password);
        long newRowId=db.insert(DBContract.Form.TABLE_NAME, null, row);
    }

}
