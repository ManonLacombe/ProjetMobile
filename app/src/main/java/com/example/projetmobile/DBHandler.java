package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the methods related to the database
 */
public class DBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Form.db";

    /**
     * Constructor for DBHandler class
     * @param context is the concerned activity
     */
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method allows to create the database
     * @param db indicates the database to be considered
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + DBContract.Form.TABLE_NAME + " (" +
                DBContract.Form._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBContract.Form.COLUMN_USER + " TEXT UNIQUE," +
                DBContract.Form.COLUMN_PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    /**
     * this method allows to delete a table if it exists
     * @param db indicates the database to be considered
     * @param oldVersion indicates the old version of the database
     * @param newVersion indicates the new version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Form.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    /**
     * this method allows you to create a line and insert it
     * @param user indicates the user name
     * @param password indicates the password
     */
    public void insertSession(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        // insertion create a row and insert it
        ContentValues row=new ContentValues();
        row.put(DBContract.Form.COLUMN_USER, user);
        row.put(DBContract.Form.COLUMN_PASSWORD, password);
        long newRowId=db.insert(DBContract.Form.TABLE_NAME, null, row);
    }

    /**
     * This method allows to select a CoupleId by his user name
     * @param userDonner is the user name
     * @param mdpDonner is the password
     * @return a CoupleId in list form but there is only one
     */
    public List<CoupleId> selectByUser(String userDonner, String mdpDonner) {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] projection = {
                DBContract.Form.COLUMN_USER,
                DBContract.Form.COLUMN_PASSWORD
        };
        String selection = DBContract.Form.COLUMN_USER+" LIKE ? AND "+
                DBContract.Form.COLUMN_PASSWORD+ " LIKE ?" ;
        String [] selectionArgs = {userDonner, mdpDonner};
        Cursor cursor=db.query(
                DBContract.Form.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        List<CoupleId> couples = new ArrayList<>();
        while(cursor.moveToNext()){
            @SuppressLint("Range") String user=cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_USER));
            @SuppressLint("Range") String password=cursor.getString(cursor.getColumnIndex(DBContract.Form.COLUMN_PASSWORD));
            CoupleId tmp=new CoupleId(user, password);
            couples.add(tmp);
        }
        cursor.close();
        return couples;
    }
}