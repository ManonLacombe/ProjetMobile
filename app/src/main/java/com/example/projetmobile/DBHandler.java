package com.example.projetmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * cette classe définit définit les méthodes liées à la base de données
 */
public class DBHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Form.db";

    /**
     * ???
     * @param context
     */
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * cette méthode permet de créer la base de données
     * @param db indique la base de donnée à prendre en compte
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
     * cette méthode permet de supprimer une table si elle existe
     * @param db indique la base de données à prendre en compte
     * @param oldVersion indique l'ancienne version de la base de données
     * @param newVersion indique la nouvelle version de la base de données
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DBContract.Form.TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    /**
     * cette méthode permet de créer une ligne et de l'insérer
     * @param user indique le nom d'utilisateur
     * @param password indique le mot de passe
     */
    public void insertSession(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        // insertion create a row and insert it
        ContentValues row=new ContentValues();
        row.put(DBContract.Form.COLUMN_USER, user);
        row.put(DBContract.Form.COLUMN_PASSWORD, password);
        long newRowId=db.insert(DBContract.Form.TABLE_NAME, null, row);
    }
}